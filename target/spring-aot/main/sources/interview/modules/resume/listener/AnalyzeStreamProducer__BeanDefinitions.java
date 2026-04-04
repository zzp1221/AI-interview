package interview.modules.resume.listener;

import interview.infrastructure.redis.RedisService;
import interview.modules.resume.repository.ResumeRepository;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link AnalyzeStreamProducer}.
 */
@Generated
public class AnalyzeStreamProducer__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'analyzeStreamProducer'.
   */
  private static BeanInstanceSupplier<AnalyzeStreamProducer> getAnalyzeStreamProducerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<AnalyzeStreamProducer>forConstructor(RedisService.class, ResumeRepository.class)
            .withGenerator((registeredBean, args) -> new AnalyzeStreamProducer(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'analyzeStreamProducer'.
   */
  public static BeanDefinition getAnalyzeStreamProducerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(AnalyzeStreamProducer.class);
    beanDefinition.setInstanceSupplier(getAnalyzeStreamProducerInstanceSupplier());
    return beanDefinition;
  }
}
