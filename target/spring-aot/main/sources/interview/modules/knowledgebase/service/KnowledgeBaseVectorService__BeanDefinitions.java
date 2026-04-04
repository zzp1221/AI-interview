package interview.modules.knowledgebase.service;

import interview.modules.knowledgebase.repository.VectorRepository;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link KnowledgeBaseVectorService}.
 */
@Generated
public class KnowledgeBaseVectorService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'knowledgeBaseVectorService'.
   */
  private static BeanInstanceSupplier<KnowledgeBaseVectorService> getKnowledgeBaseVectorServiceInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<KnowledgeBaseVectorService>forConstructor(VectorStore.class, VectorRepository.class)
            .withGenerator((registeredBean, args) -> new KnowledgeBaseVectorService(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'knowledgeBaseVectorService'.
   */
  public static BeanDefinition getKnowledgeBaseVectorServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(KnowledgeBaseVectorService.class);
    beanDefinition.setInstanceSupplier(getKnowledgeBaseVectorServiceInstanceSupplier());
    return beanDefinition;
  }
}
