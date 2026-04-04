package org.springframework.boot.data.redis.autoconfigure;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link DataRedisRepositoriesAutoConfiguration}.
 */
@Generated
public class DataRedisRepositoriesAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'dataRedisRepositoriesAutoConfiguration'.
   */
  public static BeanDefinition getDataRedisRepositoriesAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(DataRedisRepositoriesAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(DataRedisRepositoriesAutoConfiguration::new);
    return beanDefinition;
  }
}
