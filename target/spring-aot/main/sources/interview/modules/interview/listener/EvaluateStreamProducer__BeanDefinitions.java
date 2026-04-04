package interview.modules.interview.listener;

import interview.infrastructure.redis.RedisService;
import interview.modules.interview.repository.InterviewSessionRepository;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link EvaluateStreamProducer}.
 */
@Generated
public class EvaluateStreamProducer__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'evaluateStreamProducer'.
   */
  private static BeanInstanceSupplier<EvaluateStreamProducer> getEvaluateStreamProducerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<EvaluateStreamProducer>forConstructor(RedisService.class, InterviewSessionRepository.class)
            .withGenerator((registeredBean, args) -> new EvaluateStreamProducer(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'evaluateStreamProducer'.
   */
  public static BeanDefinition getEvaluateStreamProducerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(EvaluateStreamProducer.class);
    beanDefinition.setInstanceSupplier(getEvaluateStreamProducerInstanceSupplier());
    return beanDefinition;
  }
}
