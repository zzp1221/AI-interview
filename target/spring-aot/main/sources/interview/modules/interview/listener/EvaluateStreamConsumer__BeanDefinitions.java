package interview.modules.interview.listener;

import interview.infrastructure.redis.RedisService;
import interview.modules.interview.repository.InterviewSessionRepository;
import interview.modules.interview.service.AnswerEvaluationService;
import interview.modules.interview.service.InterviewPersistenceService;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import tools.jackson.databind.ObjectMapper;

/**
 * Bean definitions for {@link EvaluateStreamConsumer}.
 */
@Generated
public class EvaluateStreamConsumer__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'evaluateStreamConsumer'.
   */
  private static BeanInstanceSupplier<EvaluateStreamConsumer> getEvaluateStreamConsumerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<EvaluateStreamConsumer>forConstructor(RedisService.class, InterviewSessionRepository.class, AnswerEvaluationService.class, InterviewPersistenceService.class, ObjectMapper.class)
            .withGenerator((registeredBean, args) -> new EvaluateStreamConsumer(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4)));
  }

  /**
   * Get the bean definition for 'evaluateStreamConsumer'.
   */
  public static BeanDefinition getEvaluateStreamConsumerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(EvaluateStreamConsumer.class);
    beanDefinition.setInitMethodNames("init");
    beanDefinition.setDestroyMethodNames("destroy");
    beanDefinition.setInstanceSupplier(getEvaluateStreamConsumerInstanceSupplier());
    return beanDefinition;
  }
}
