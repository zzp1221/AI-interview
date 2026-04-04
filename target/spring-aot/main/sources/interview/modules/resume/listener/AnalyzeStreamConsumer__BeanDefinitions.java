package interview.modules.resume.listener;

import interview.infrastructure.redis.RedisService;
import interview.modules.resume.repository.ResumeRepository;
import interview.modules.resume.service.ResumeGradingService;
import interview.modules.resume.service.ResumePersistenceService;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link AnalyzeStreamConsumer}.
 */
@Generated
public class AnalyzeStreamConsumer__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'analyzeStreamConsumer'.
   */
  private static BeanInstanceSupplier<AnalyzeStreamConsumer> getAnalyzeStreamConsumerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<AnalyzeStreamConsumer>forConstructor(RedisService.class, ResumeGradingService.class, ResumePersistenceService.class, ResumeRepository.class)
            .withGenerator((registeredBean, args) -> new AnalyzeStreamConsumer(args.get(0), args.get(1), args.get(2), args.get(3)));
  }

  /**
   * Get the bean definition for 'analyzeStreamConsumer'.
   */
  public static BeanDefinition getAnalyzeStreamConsumerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(AnalyzeStreamConsumer.class);
    beanDefinition.setInitMethodNames("init");
    beanDefinition.setDestroyMethodNames("destroy");
    beanDefinition.setInstanceSupplier(getAnalyzeStreamConsumerInstanceSupplier());
    return beanDefinition;
  }
}
