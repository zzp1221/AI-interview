package interview.modules.knowledgebase.service;

import interview.modules.knowledgebase.repository.KnowledgeBaseRepository;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link KnowledgeBasePersistenceService}.
 */
@Generated
public class KnowledgeBasePersistenceService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'knowledgeBasePersistenceService'.
   */
  private static BeanInstanceSupplier<KnowledgeBasePersistenceService> getKnowledgeBasePersistenceServiceInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<KnowledgeBasePersistenceService>forConstructor(KnowledgeBaseRepository.class)
            .withGenerator((registeredBean, args) -> new KnowledgeBasePersistenceService(args.get(0)));
  }

  /**
   * Get the bean definition for 'knowledgeBasePersistenceService'.
   */
  public static BeanDefinition getKnowledgeBasePersistenceServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(KnowledgeBasePersistenceService.class);
    beanDefinition.setInstanceSupplier(getKnowledgeBasePersistenceServiceInstanceSupplier());
    return beanDefinition;
  }
}
