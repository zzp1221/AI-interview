package org.springframework.ai.retry.autoconfigure;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link SpringAiRetryProperties}.
 */
@Generated
public class SpringAiRetryProperties__BeanDefinitions {
  /**
   * Get the bean definition for 'springAiRetryProperties'.
   */
  public static BeanDefinition getSpringAiRetryPropertiesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(SpringAiRetryProperties.class);
    beanDefinition.setInstanceSupplier(SpringAiRetryProperties::new);
    return beanDefinition;
  }
}
