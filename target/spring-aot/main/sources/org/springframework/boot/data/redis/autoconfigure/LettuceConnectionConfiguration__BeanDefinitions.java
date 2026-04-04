package org.springframework.boot.data.redis.autoconfigure;

import io.lettuce.core.resource.DefaultClientResources;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link LettuceConnectionConfiguration}.
 */
@Generated
public class LettuceConnectionConfiguration__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'org.springframework.boot.data.redis.autoconfigure.LettuceConnectionConfiguration'.
   */
  private static BeanInstanceSupplier<LettuceConnectionConfiguration> getLettuceConnectionConfigurationInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<LettuceConnectionConfiguration>forConstructor(DataRedisProperties.class, ObjectProvider.class, ObjectProvider.class, ObjectProvider.class, ObjectProvider.class, DataRedisConnectionDetails.class)
            .withGenerator((registeredBean, args) -> new LettuceConnectionConfiguration(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4), args.get(5)));
  }

  /**
   * Get the bean definition for 'lettuceConnectionConfiguration'.
   */
  public static BeanDefinition getLettuceConnectionConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(LettuceConnectionConfiguration.class);
    beanDefinition.setInstanceSupplier(getLettuceConnectionConfigurationInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'lettuceClientResources'.
   */
  private static BeanInstanceSupplier<DefaultClientResources> getLettuceClientResourcesInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<DefaultClientResources>forFactoryMethod(LettuceConnectionConfiguration.class, "lettuceClientResources", ObjectProvider.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.boot.data.redis.autoconfigure.LettuceConnectionConfiguration", LettuceConnectionConfiguration.class).lettuceClientResources(args.get(0)));
  }

  /**
   * Get the bean definition for 'lettuceClientResources'.
   */
  public static BeanDefinition getLettuceClientResourcesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(DefaultClientResources.class);
    beanDefinition.setDestroyMethodNames("shutdown");
    beanDefinition.setFactoryBeanName("org.springframework.boot.data.redis.autoconfigure.LettuceConnectionConfiguration");
    beanDefinition.setInstanceSupplier(getLettuceClientResourcesInstanceSupplier());
    return beanDefinition;
  }
}
