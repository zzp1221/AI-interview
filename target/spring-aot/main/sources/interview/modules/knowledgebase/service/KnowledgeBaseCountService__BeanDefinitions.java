package interview.modules.knowledgebase.service;

import interview.modules.knowledgebase.repository.KnowledgeBaseRepository;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link KnowledgeBaseCountService}.
 */
@Generated
public class KnowledgeBaseCountService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'knowledgeBaseCountService'.
   */
  private static BeanInstanceSupplier<KnowledgeBaseCountService> getKnowledgeBaseCountServiceInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<KnowledgeBaseCountService>forConstructor(KnowledgeBaseRepository.class)
            .withGenerator((registeredBean, args) -> new KnowledgeBaseCountService(args.get(0)));
  }

  /**
   * Get the bean definition for 'knowledgeBaseCountService'.
   */
  public static BeanDefinition getKnowledgeBaseCountServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(KnowledgeBaseCountService.class);
    beanDefinition.setInstanceSupplier(getKnowledgeBaseCountServiceInstanceSupplier());
    return beanDefinition;
  }
}
