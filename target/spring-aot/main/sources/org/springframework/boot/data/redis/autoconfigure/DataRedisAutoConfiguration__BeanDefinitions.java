package org.springframework.boot.data.redis.autoconfigure;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link DataRedisAutoConfiguration}.
 */
@Generated
public class DataRedisAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'dataRedisAutoConfiguration'.
   */
  public static BeanDefinition getDataRedisAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(DataRedisAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(DataRedisAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'redisConnectionDetails'.
   */
  private static BeanInstanceSupplier<PropertiesDataRedisConnectionDetails> getRedisConnectionDetailsInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<PropertiesDataRedisConnectionDetails>forFactoryMethod(DataRedisAutoConfiguration.class, "redisConnectionDetails", DataRedisProperties.class, ObjectProvider.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.boot.data.redis.autoconfigure.DataRedisAutoConfiguration", DataRedisAutoConfiguration.class).redisConnectionDetails(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'redisConnectionDetails'.
   */
  public static BeanDefinition getRedisConnectionDetailsBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(PropertiesDataRedisConnectionDetails.class);
    beanDefinition.setFactoryBeanName("org.springframework.boot.data.redis.autoconfigure.DataRedisAutoConfiguration");
    beanDefinition.setInstanceSupplier(getRedisConnectionDetailsInstanceSupplier());
    return beanDefinition;
  }
}
