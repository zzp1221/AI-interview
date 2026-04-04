package interview.modules.knowledgebase.service;

import interview.infrastructure.file.FileStorageService;
import interview.modules.knowledgebase.repository.KnowledgeBaseRepository;
import interview.modules.knowledgebase.repository.RagChatSessionRepository;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link KnowledgeBaseDeleteService}.
 */
@Generated
public class KnowledgeBaseDeleteService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'knowledgeBaseDeleteService'.
   */
  private static BeanInstanceSupplier<KnowledgeBaseDeleteService> getKnowledgeBaseDeleteServiceInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<KnowledgeBaseDeleteService>forConstructor(KnowledgeBaseRepository.class, RagChatSessionRepository.class, KnowledgeBaseVectorService.class, FileStorageService.class)
            .withGenerator((registeredBean, args) -> new KnowledgeBaseDeleteService(args.get(0), args.get(1), args.get(2), args.get(3)));
  }

  /**
   * Get the bean definition for 'knowledgeBaseDeleteService'.
   */
  public static BeanDefinition getKnowledgeBaseDeleteServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(KnowledgeBaseDeleteService.class);
    beanDefinition.setInstanceSupplier(getKnowledgeBaseDeleteServiceInstanceSupplier());
    return beanDefinition;
  }
}
