package interview.infrastructure.file;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link TextCleaningService}.
 */
@Generated
public class TextCleaningService__BeanDefinitions {
  /**
   * Get the bean definition for 'textCleaningService'.
   */
  public static BeanDefinition getTextCleaningServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(TextCleaningService.class);
    beanDefinition.setInstanceSupplier(TextCleaningService::new);
    return beanDefinition;
  }
}
