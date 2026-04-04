package org.redisson.spring.starter;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link RedissonProperties}.
 */
@Generated
public class RedissonProperties__BeanDefinitions {
  /**
   * Get the bean definition for 'redissonProperties'.
   */
  public static BeanDefinition getRedissonPropertiesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(RedissonProperties.class);
    beanDefinition.setInstanceSupplier(RedissonProperties::new);
    return beanDefinition;
  }
}
