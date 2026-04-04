package interview.common.async;

import interview.common.constant.AsyncTaskStreamConstants;
import interview.infrastructure.redis.RedisService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.stream.StreamMessageId;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public abstract class AbstractStreamConsumer<T> {
    private final RedisService redisService;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private ExecutorService executor;
    private String consumerName;
    protected AbstractStreamConsumer( RedisService redisService) {
        this.redisService = redisService;
    }



    @PostConstruct
    public void init() {
        this.consumerName = consumerPrefix() + UUID.randomUUID()
                .toString().substring(0, 8);

        try {
            redisService.createStreamGroup(streamKey(),groupName());
            log.info("消费者组已创建或存在:{}",groupName());
        }catch (Exception e) {
            log.warn("创建消费者组出现异常:{}",e.getMessage());
        }

        this.executor = Executors.newSingleThreadExecutor(r->{
            Thread thread = new Thread(r);
            thread.setName(threadName());
            thread.setDaemon(true);
            return thread;
        });
        //TODO：最好使用ThreadPoolExecutor创建线程池
        running.set(true);
        executor.submit(this::consumeLoop);
        log.info("{}消费者已启动：consumerName={}",taskDisplayName(),consumerName);
    }






    @PreDestroy
    public void destroy() {
        running.set(false);
        if (executor != null) {
            executor.shutdown();
        }
        log.info("{}消费者已关闭consumerName={}",taskDisplayName(),consumerName);

    }

    private void consumeLoop() {
        while (running.get()) {
            try {
                redisService.streamConsumeMessages(
                        streamKey(),
                        groupName(),
                        consumerName,
                        AsyncTaskStreamConstants.BATCH_SIZE,
                        AsyncTaskStreamConstants.POLL_INTERVAL_MS,
                        this::processMessage
                );
            }catch (Exception e){
                if (Thread.currentThread().isInterrupted()) {
                    log.info("消费者线程被中断");
                    break;
                }
                log.error("消费消息时发生错误：{}",e.getMessage(),e);
                try {
                    Thread.sleep(AsyncTaskStreamConstants.POLL_INTERVAL_MS);
                } catch (InterruptedException interruptedException) {
                    Thread.currentThread().interrupt();
                    log.info("消费者线程被中断");
                    break;
                }
            }
        }
    }

    private void processMessage(StreamMessageId streamMessageId,Map<String,String> data) {
        T payload = parsePayload(streamMessageId,data);
        if (payload == null) {
            ackMessage(streamMessageId);
            return;
        }

        int retryCount = parseRetryCount(data);
        log.info("开始处理{}任务：{}，messageId={},retryCount={}",taskDisplayName(),payloadIdentifier(payload),streamMessageId,retryCount);

        try {
            markProcessing(payload);
            processBusiness(payload);
            markCompleted(payload);
            ackMessage(streamMessageId);
            log.info("{}任务完成：{}",taskDisplayName(),payloadIdentifier(payload));

        }catch (Exception e) {
            log.error("{}任务失败：{}，error{}",taskDisplayName(),payloadIdentifier(payload),e.getMessage());
            if (retryCount < AsyncTaskStreamConstants.MAX_RETRY_COUNT) {
                retryMessage(payload,retryCount+1);//重新入队
            }else {
                markFailed(payload,truncateError(
                        taskDisplayName()+"失败（已重试"+retryCount+"次）："+e.getMessage()
                ));
            }
            //TODO: 当retryCount>AsyncTaskStreamConstants.MAX_RETRY_COUNT时，将失败消息转入死信队列
            ackMessage(streamMessageId);//失败也要ack
        }
    }




    protected int parseRetryCount(Map<String, String> data) {
        try {
            return Integer.parseInt(data.getOrDefault(AsyncTaskStreamConstants.FIELD_RETRY_COUNT, "0"));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    protected String truncateError(String error) {
        if (error == null) {
            return null;
        }
        return error.length() > 500 ? error.substring(0, 500) : error;
    }

    private void ackMessage(StreamMessageId messageId) {
        try {
            redisService.streamAck(streamKey(), groupName(), messageId);
        } catch (Exception e) {
            log.error("确认消息失败: messageId={}, error={}", messageId, e.getMessage(), e);
        }
    }

    protected RedisService redisService() {
        return redisService;
    }

    protected abstract String taskDisplayName();

    protected abstract String streamKey();

    protected abstract String groupName();

    protected abstract String consumerPrefix();

    protected abstract String threadName();

    protected abstract T parsePayload(StreamMessageId messageId, Map<String, String> data);

    protected abstract String payloadIdentifier(T payload);

    protected abstract void markProcessing(T payload);

    protected abstract void processBusiness(T payload);

    protected abstract void markCompleted(T payload);

    protected abstract void markFailed(T payload, String error);

    protected abstract void retryMessage(T payload, int retryCount);
}
