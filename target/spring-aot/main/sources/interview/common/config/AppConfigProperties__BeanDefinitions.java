package interview.common.config;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link AppConfigProperties}.
 */
@Generated
public class AppConfigProperties__BeanDefinitions {
  /**
   * Get the bean definition for 'appConfigProperties'.
   */
  public static BeanDefinition getAppConfigPropertiesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(AppConfigProperties.class);
    beanDefinition.setInstanceSupplier(AppConfigProperties::new);
    return beanDefinition;
  }
}
