package org.springframework.boot.data.redis.autoconfigure;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link DataRedisProperties}.
 */
@Generated
public class DataRedisProperties__BeanDefinitions {
  /**
   * Get the bean definition for 'dataRedisProperties'.
   */
  public static BeanDefinition getDataRedisPropertiesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(DataRedisProperties.class);
    beanDefinition.setInstanceSupplier(DataRedisProperties::new);
    return beanDefinition;
  }
}
