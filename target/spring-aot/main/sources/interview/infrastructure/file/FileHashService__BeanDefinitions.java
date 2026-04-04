package interview.infrastructure.file;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link FileHashService}.
 */
@Generated
public class FileHashService__BeanDefinitions {
  /**
   * Get the bean definition for 'fileHashService'.
   */
  public static BeanDefinition getFileHashServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(FileHashService.class);
    beanDefinition.setInstanceSupplier(FileHashService::new);
    return beanDefinition;
  }
}
