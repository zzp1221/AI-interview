package interview.modules.knowledgebase.listener;

import interview.infrastructure.redis.RedisService;
import interview.modules.knowledgebase.repository.KnowledgeBaseRepository;
import interview.modules.knowledgebase.service.KnowledgeBaseVectorService;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link VectorizeStreamConsumer}.
 */
@Generated
public class VectorizeStreamConsumer__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'vectorizeStreamConsumer'.
   */
  private static BeanInstanceSupplier<VectorizeStreamConsumer> getVectorizeStreamConsumerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<VectorizeStreamConsumer>forConstructor(RedisService.class, KnowledgeBaseVectorService.class, KnowledgeBaseRepository.class)
            .withGenerator((registeredBean, args) -> new VectorizeStreamConsumer(args.get(0), args.get(1), args.get(2)));
  }

  /**
   * Get the bean definition for 'vectorizeStreamConsumer'.
   */
  public static BeanDefinition getVectorizeStreamConsumerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(VectorizeStreamConsumer.class);
    beanDefinition.setInitMethodNames("init");
    beanDefinition.setDestroyMethodNames("destroy");
    beanDefinition.setInstanceSupplier(getVectorizeStreamConsumerInstanceSupplier());
    return beanDefinition;
  }
}
