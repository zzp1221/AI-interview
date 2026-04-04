package interview.modules.knowledgebase.service;

import interview.infrastructure.file.ContentTypeDetectionService;
import interview.infrastructure.file.DocumentParseService;
import interview.infrastructure.file.FileStorageService;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link KnowledgeBaseParseService}.
 */
@Generated
public class KnowledgeBaseParseService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'knowledgeBaseParseService'.
   */
  private static BeanInstanceSupplier<KnowledgeBaseParseService> getKnowledgeBaseParseServiceInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<KnowledgeBaseParseService>forConstructor(DocumentParseService.class, ContentTypeDetectionService.class, FileStorageService.class)
            .withGenerator((registeredBean, args) -> new KnowledgeBaseParseService(args.get(0), args.get(1), args.get(2)));
  }

  /**
   * Get the bean definition for 'knowledgeBaseParseService'.
   */
  public static BeanDefinition getKnowledgeBaseParseServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(KnowledgeBaseParseService.class);
    beanDefinition.setInstanceSupplier(getKnowledgeBaseParseServiceInstanceSupplier());
    return beanDefinition;
  }
}
