package interview.modules.knowledgebase.listener;

import interview.infrastructure.redis.RedisService;
import interview.modules.knowledgebase.repository.KnowledgeBaseRepository;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link VectorizeStreamProducer}.
 */
@Generated
public class VectorizeStreamProducer__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'vectorizeStreamProducer'.
   */
  private static BeanInstanceSupplier<VectorizeStreamProducer> getVectorizeStreamProducerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<VectorizeStreamProducer>forConstructor(RedisService.class, KnowledgeBaseRepository.class)
            .withGenerator((registeredBean, args) -> new VectorizeStreamProducer(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'vectorizeStreamProducer'.
   */
  public static BeanDefinition getVectorizeStreamProducerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(VectorizeStreamProducer.class);
    beanDefinition.setInstanceSupplier(getVectorizeStreamProducerInstanceSupplier());
    return beanDefinition;
  }
}
