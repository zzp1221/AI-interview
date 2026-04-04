package interview.modules.knowledgebase.service;

import interview.infrastructure.file.FileHashService;
import interview.infrastructure.file.FileStorageService;
import interview.infrastructure.file.FileValidationService;
import interview.modules.knowledgebase.listener.VectorizeStreamProducer;
import interview.modules.knowledgebase.repository.KnowledgeBaseRepository;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link KnowledgeBaseUploadService}.
 */
@Generated
public class KnowledgeBaseUploadService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'knowledgeBaseUploadService'.
   */
  private static BeanInstanceSupplier<KnowledgeBaseUploadService> getKnowledgeBaseUploadServiceInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<KnowledgeBaseUploadService>forConstructor(KnowledgeBaseParseService.class, KnowledgeBasePersistenceService.class, FileStorageService.class, KnowledgeBaseRepository.class, FileValidationService.class, FileHashService.class, VectorizeStreamProducer.class)
            .withGenerator((registeredBean, args) -> new KnowledgeBaseUploadService(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4), args.get(5), args.get(6)));
  }

  /**
   * Get the bean definition for 'knowledgeBaseUploadService'.
   */
  public static BeanDefinition getKnowledgeBaseUploadServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(KnowledgeBaseUploadService.class);
    beanDefinition.setInstanceSupplier(getKnowledgeBaseUploadServiceInstanceSupplier());
    return beanDefinition;
  }
}
