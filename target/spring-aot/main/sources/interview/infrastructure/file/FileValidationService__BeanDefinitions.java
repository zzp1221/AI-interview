package interview.infrastructure.file;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link FileValidationService}.
 */
@Generated
public class FileValidationService__BeanDefinitions {
  /**
   * Get the bean definition for 'fileValidationService'.
   */
  public static BeanDefinition getFileValidationServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(FileValidationService.class);
    beanDefinition.setInstanceSupplier(FileValidationService::new);
    return beanDefinition;
  }
}
