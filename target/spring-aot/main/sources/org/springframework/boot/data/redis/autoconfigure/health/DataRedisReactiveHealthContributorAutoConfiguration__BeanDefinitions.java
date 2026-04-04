package org.springframework.boot.data.redis.autoconfigure.health;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.health.contributor.ReactiveHealthContributor;

/**
 * Bean definitions for {@link DataRedisReactiveHealthContributorAutoConfiguration}.
 */
@Generated
public class DataRedisReactiveHealthContributorAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'dataRedisReactiveHealthContributorAutoConfiguration'.
   */
  public static BeanDefinition getDataRedisReactiveHealthContributorAutoConfigurationBeanDefinition(
      ) {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(DataRedisReactiveHealthContributorAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(DataRedisReactiveHealthContributorAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'redisHealthContributor'.
   */
  private static BeanInstanceSupplier<ReactiveHealthContributor> getRedisHealthContributorInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ReactiveHealthContributor>forFactoryMethod(DataRedisReactiveHealthContributorAutoConfiguration.class, "redisHealthContributor", ConfigurableListableBeanFactory.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.boot.data.redis.autoconfigure.health.DataRedisReactiveHealthContributorAutoConfiguration", DataRedisReactiveHealthContributorAutoConfiguration.class).redisHealthContributor(args.get(0)));
  }

  /**
   * Get the bean definition for 'redisHealthContributor'.
   */
  public static BeanDefinition getRedisHealthContributorBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ReactiveHealthContributor.class);
    beanDefinition.setFactoryBeanName("org.springframework.boot.data.redis.autoconfigure.health.DataRedisReactiveHealthContributorAutoConfiguration");
    beanDefinition.setInstanceSupplier(getRedisHealthContributorInstanceSupplier());
    return beanDefinition;
  }
}
