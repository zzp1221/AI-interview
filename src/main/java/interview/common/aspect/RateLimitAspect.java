package interview.common.aspect;

import interview.common.annotation.RateLimit;
import interview.common.exception.RateLimitExceededException;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RScript;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 使用lua脚本和流动窗口算法完成限流
 */
@RequiredArgsConstructor
@Component
@Aspect
@Slf4j
public class RateLimitAspect {


    private final RedissonClient redissonClient;

    /**
     * findFallbackMethod 使用了反射查找方法。虽然在限流触发时（即系统压力大时）才调用，但反射本身有性能损耗。
     * 所以我决定在切面初始化时缓存方法的 Method 对象，提高性能
     */
    private final ConcurrentHashMap<String, Method> fallbackMethodCache = new ConcurrentHashMap<>();

    /**
     * 获取客户端真实 IP
     * 处理 X-Forwarded-For 头，支持代理服务器场景
     */
    private String getClientIp() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return "unknown";
        }

        HttpServletRequest request = attributes.getRequest();
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        // 处理多个 IP 的情况（X-Forwarded-For 可能包含多个 IP）
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }

        return ip != null ? ip : "unknown";
    }

    /**
     * 获取当前用户 ID
     * 从请求属性或 Session 中获取
     */
    private String getCurrentUserId() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return "anonymous";
        }

        HttpServletRequest request = attributes.getRequest();
        Object userId = request.getAttribute("userId");
        if (userId != null) {
            return userId.toString();
        }
        return "anonymous";
    }

    /**
     * 生成限流键列表
     */
    private List<String> generateKeys(String className, String methodName, RateLimit.Dimension[] dimensions) {
        List<String> keys = new ArrayList<>();
        // 使用 {} 包含类名和方法名作为 Hash Tag，确保该方法的所有限流 Key 落在同一个 Redis Slot
        // 从而适配 Redis Cluster 模式
        String hashTag = "{" + className + ":" + methodName + "}";
        String keyPrefix = "ratelimit:" + hashTag;

        for (RateLimit.Dimension dimension : dimensions) {
            switch (dimension) {
                case GLOBAL -> keys.add(keyPrefix + ":global");
                case IP -> keys.add(keyPrefix + ":ip:" + getClientIp());
                case USER -> keys.add(keyPrefix + ":user:" + getCurrentUserId());
            }
        }

        return keys;
    }

    /**
     * 计算时间窗口毫秒数
     */
    private long calculateIntervalMs(long interval, RateLimit.TimeUnit unit) {
        return switch (unit) {
            case MILLISECONDS -> interval;
            case SECONDS -> interval * 1000;
            case MINUTES -> interval * 60 * 1000;
            case HOURS -> interval * 3600 * 1000;
            case DAYS -> interval * 86400 * 1000;
        };
    }

    /**
     * 将结果对象安全转换为 Long
     */
    private Long convertToLong(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Long) {
            return (Long) obj;
        } else if (obj instanceof Integer) {
            return ((Integer) obj).longValue();
        } else if (obj instanceof Short) {
            return ((Short) obj).longValue();
        } else if (obj instanceof Byte) {
            return ((Byte) obj).longValue();
        } else if (obj instanceof String) {
            try {
                return Long.parseLong((String) obj);
            } catch (NumberFormatException e) {
                log.warn("无法将字符串转换为Long: {}", obj);
                return null;
            }
        }
        log.warn("不支持的对象类型转换为Long: {}", obj.getClass().getName());
        return null;
    }

    /**
     * 处理限流超出情况
     */
    private Object handleRateLimitExceeded(ProceedingJoinPoint joinPoint, RateLimit rateLimit, List<String> keys)
            throws Throwable {
        String methodName = joinPoint.getSignature().getName();

        // 如果配置了降级方法，则调用降级方法
        if (rateLimit.fallback() != null && !rateLimit.fallback().isEmpty()) {
            try {
                Method fallbackMethod = findFallbackMethod(joinPoint, rateLimit.fallback());
                if (fallbackMethod != null) {
                    log.debug("限流触发，执行降级方法: {}.{} -> {}",
                            joinPoint.getTarget().getClass().getSimpleName(),
                            methodName,
                            rateLimit.fallback());
                    // 如果降级方法有参数，传入原方法的参数
                    if (fallbackMethod.getParameterCount() > 0) {
                        return fallbackMethod.invoke(joinPoint.getTarget(), joinPoint.getArgs());
                    } else {
                        return fallbackMethod.invoke(joinPoint.getTarget());
                    }
                }
            } catch (Exception e) {
                log.error("降级方法执行失败: {}", rateLimit.fallback(), e);
            }
        }

        // 没有降级方法或降级失败，抛出限流异常
        log.debug("限流触发，拒绝请求: keys={}, count={} per {} {}",
                keys, rateLimit.count(), rateLimit.interval(), rateLimit.timeUnit());
        throw new RateLimitExceededException("请求过于频繁，请稍后再试");
    }

    /**
     * 查找降级方法
     * 优先查找与原方法参数列表完全一致的方法，找不到则查找无参方法
     */
    private Method findFallbackMethod(ProceedingJoinPoint joinPoint, String fallbackName) {
        Class<?> targetClass = joinPoint.getTarget().getClass();
        String cacheKey = targetClass.getName() + "#" + fallbackName;

        return fallbackMethodCache.computeIfAbsent(cacheKey, k -> {
            Method method = null;
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Class<?>[] parameterTypes = signature.getParameterTypes();

            try {
                // 1. 尝试查找同参数列表的方法
                method = targetClass.getDeclaredMethod(fallbackName, parameterTypes);
                method.setAccessible(true);
                return method;
            } catch (NoSuchMethodException e) {
                // 2. 尝试查找无参方法
                try {
                    method = targetClass.getDeclaredMethod(fallbackName);
                    method.setAccessible(true);
                    return method;
                } catch (NoSuchMethodException ex) {
                    log.warn("未找到降级方法: {}.{} (需无参或参数列表一致)",
                            targetClass.getSimpleName(), fallbackName);
                    return null;
                }
            }
        });
    }

    /**
     * Lua脚本缓存
     */
    private static String LUA_SCRIPT;
    private  String LuaScriptSha;

    static {
        try {
            ClassPathResource classPathResource = new ClassPathResource("scripts/rate_limit.lua");
            LUA_SCRIPT = new String(classPathResource.getContentAsByteArray(), StandardCharsets.UTF_8);
        }catch (Exception e){
            throw new RuntimeException("脚本加载异常");
        }
    }

    /**
     * 预加载脚本到redis提高性能
     */
    @PostConstruct
    public void init(){
        this.LuaScriptSha = redissonClient.getScript(StringCodec.INSTANCE).scriptLoad(LUA_SCRIPT);
        log.info("Lua脚本预加载完成,:SHA:{}", this.LuaScriptSha);
    }

    /**
     * 环绕通知，拦截带@RateLimit
     * 增加对 Redis 异常的捕获逻辑，保证核心业务可用，防止雪崩。
     */
    @Around("@annotation(rateLimit)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, RateLimit rateLimit) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        String className = method.getDeclaringClass().getName();
        String methodName = method.getName();

        //1.计算时间窗口
        long interval = calculateIntervalMs(rateLimit.interval(),rateLimit.timeUnit());

        //2.根据配置维度动态生成Redis Key
        List<String> keys = generateKeys(className, methodName, rateLimit.dimensions());

        //准备参数
        List<Object> list = new ArrayList<>(keys);
        Object[] args = {
                String.valueOf(System.currentTimeMillis()), //args[0]当前时间戳
                String.valueOf(1), //args[1]申请令牌数（默认为1）
                String.valueOf(interval), //args[2] 时间窗口
                String.valueOf(rateLimit.count()), //args[3] 最大令牌数
                UUID.randomUUID().toString() //args[4] 唯一身份标识
        };

        try {
            // 3. 调用 Lua 脚本执行限流
            RScript script = redissonClient.getScript();
            Object resultObject = script.evalSha(
                    RScript.Mode.READ_WRITE,
                    LuaScriptSha,
                    RScript.ReturnType.VALUE,
                    list,
                    args
            );
            Long result = convertToLong(resultObject);
            if (result == null || result == 0) {
                return handleRateLimitExceeded(proceedingJoinPoint, rateLimit, keys);
            }
            // 限流通过，执行业务
            return proceedingJoinPoint.proceed();
        }catch (Exception e){
            //捕获所有异常
            log.error("【限流组件熔断】Redis执行异常，为保证业务可用，暂时放行请求。Error: {}", e.getMessage(), e);
            // 直接放行，让业务继续，防止雪崩
            try {
                return proceedingJoinPoint.proceed();
            } catch (Throwable throwable) {
                // 如果业务执行也报错，抛出原限流异常
                throw e;
            }
        }


    }
    //对于around方法未处理雪崩时代码为
    //RScript script = redissonClient.getScript();
    //Object resultObject = script.evalSha(
    //                RScript.Mode.READ_WRITE,
    //                LuaScriptSha,
    //                RScript.ReturnType.VALUE,
    //                list,
    //                args
    //        );
    //
    //        Long result = convertToLong(resultObject);
    //        if (result == null||result==0) {
    //            return handleRateLimitExceeded(proceedingJoinPoint, rateLimit, keys);
    //        }
    //        return proceedingJoinPoint.proceed();

}
