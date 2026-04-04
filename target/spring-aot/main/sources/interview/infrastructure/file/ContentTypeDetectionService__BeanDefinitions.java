package interview.infrastructure.file;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ContentTypeDetectionService}.
 */
@Generated
public class ContentTypeDetectionService__BeanDefinitions {
  /**
   * Get the bean definition for 'contentTypeDetectionService'.
   */
  public static BeanDefinition getContentTypeDetectionServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ContentTypeDetectionService.class);
    beanDefinition.setInstanceSupplier(ContentTypeDetectionService::new);
    return beanDefinition;
  }
}
