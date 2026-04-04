package org.springframework.boot.data.redis.autoconfigure.health;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link DataRedisHealthContributorAutoConfiguration}.
 */
@Generated
public class DataRedisHealthContributorAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'dataRedisHealthContributorAutoConfiguration'.
   */
  public static BeanDefinition getDataRedisHealthContributorAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(DataRedisHealthContributorAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(DataRedisHealthContributorAutoConfiguration::new);
    return beanDefinition;
  }
}
