package interview.modules.knowledgebase.service;

import interview.infrastructure.file.FileStorageService;
import interview.infrastructure.mapper.KnowledgeBaseMapper;
import interview.modules.knowledgebase.repository.KnowledgeBaseRepository;
import interview.modules.knowledgebase.repository.RagChatMessageRepository;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link KnowledgeBaseListService}.
 */
@Generated
public class KnowledgeBaseListService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'knowledgeBaseListService'.
   */
  private static BeanInstanceSupplier<KnowledgeBaseListService> getKnowledgeBaseListServiceInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<KnowledgeBaseListService>forConstructor(KnowledgeBaseRepository.class, RagChatMessageRepository.class, KnowledgeBaseMapper.class, FileStorageService.class)
            .withGenerator((registeredBean, args) -> new KnowledgeBaseListService(args.get(0), args.get(1), args.get(2), args.get(3)));
  }

  /**
   * Get the bean definition for 'knowledgeBaseListService'.
   */
  public static BeanDefinition getKnowledgeBaseListServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(KnowledgeBaseListService.class);
    beanDefinition.setInstanceSupplier(getKnowledgeBaseListServiceInstanceSupplier());
    return beanDefinition;
  }
}
