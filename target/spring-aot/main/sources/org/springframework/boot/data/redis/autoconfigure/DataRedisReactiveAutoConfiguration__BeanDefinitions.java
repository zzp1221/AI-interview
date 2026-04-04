package org.springframework.boot.data.redis.autoconfigure;

import java.lang.Object;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.ResolvableType;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;

/**
 * Bean definitions for {@link DataRedisReactiveAutoConfiguration}.
 */
@Generated
public class DataRedisReactiveAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'dataRedisReactiveAutoConfiguration'.
   */
  public static BeanDefinition getDataRedisReactiveAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(DataRedisReactiveAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(DataRedisReactiveAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'reactiveRedisTemplate'.
   */
  private static BeanInstanceSupplier<ReactiveRedisTemplate> getReactiveRedisTemplateInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ReactiveRedisTemplate>forFactoryMethod(DataRedisReactiveAutoConfiguration.class, "reactiveRedisTemplate", ReactiveRedisConnectionFactory.class, ResourceLoader.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.boot.data.redis.autoconfigure.DataRedisReactiveAutoConfiguration", DataRedisReactiveAutoConfiguration.class).reactiveRedisTemplate(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'reactiveRedisTemplate'.
   */
  public static BeanDefinition getReactiveRedisTemplateBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ReactiveRedisTemplate.class);
    beanDefinition.setTargetType(ResolvableType.forClassWithGenerics(ReactiveRedisTemplate.class, Object.class, Object.class));
    beanDefinition.setFactoryBeanName("org.springframework.boot.data.redis.autoconfigure.DataRedisReactiveAutoConfiguration");
    beanDefinition.setInstanceSupplier(getReactiveRedisTemplateInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'reactiveStringRedisTemplate'.
   */
  private static BeanInstanceSupplier<ReactiveStringRedisTemplate> getReactiveStringRedisTemplateInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ReactiveStringRedisTemplate>forFactoryMethod(DataRedisReactiveAutoConfiguration.class, "reactiveStringRedisTemplate", ReactiveRedisConnectionFactory.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.boot.data.redis.autoconfigure.DataRedisReactiveAutoConfiguration", DataRedisReactiveAutoConfiguration.class).reactiveStringRedisTemplate(args.get(0)));
  }

  /**
   * Get the bean definition for 'reactiveStringRedisTemplate'.
   */
  public static BeanDefinition getReactiveStringRedisTemplateBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ReactiveStringRedisTemplate.class);
    beanDefinition.setFactoryBeanName("org.springframework.boot.data.redis.autoconfigure.DataRedisReactiveAutoConfiguration");
    beanDefinition.setInstanceSupplier(getReactiveStringRedisTemplateInstanceSupplier());
    return beanDefinition;
  }
}
