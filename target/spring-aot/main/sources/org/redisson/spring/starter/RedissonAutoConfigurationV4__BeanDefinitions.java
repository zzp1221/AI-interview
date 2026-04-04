package org.redisson.spring.starter;

import java.lang.Object;
import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.api.RedissonRxClient;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.ResolvableType;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Bean definitions for {@link RedissonAutoConfigurationV4}.
 */
@Generated
public class RedissonAutoConfigurationV4__BeanDefinitions {
  /**
   * Get the bean definition for 'redissonAutoConfigurationV4'.
   */
  public static BeanDefinition getRedissonAutoConfigurationVBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(RedissonAutoConfigurationV4.class);
    InstanceSupplier<RedissonAutoConfigurationV4> instanceSupplier = InstanceSupplier.using(RedissonAutoConfigurationV4::new);
    instanceSupplier = instanceSupplier.andThen(RedissonAutoConfigurationV4__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'redisTemplate'.
   */
  private static BeanInstanceSupplier<RedisTemplate> getRedisTemplateInstanceSupplier() {
    return BeanInstanceSupplier.<RedisTemplate>forFactoryMethod(RedissonAutoConfigurationV4.class, "redisTemplate", RedisConnectionFactory.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.redisson.spring.starter.RedissonAutoConfigurationV4", RedissonAutoConfigurationV4.class).redisTemplate(args.get(0)));
  }

  /**
   * Get the bean definition for 'redisTemplate'.
   */
  public static BeanDefinition getRedisTemplateBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(RedisTemplate.class);
    beanDefinition.setTargetType(ResolvableType.forClassWithGenerics(RedisTemplate.class, Object.class, Object.class));
    beanDefinition.setFactoryBeanName("org.redisson.spring.starter.RedissonAutoConfigurationV4");
    beanDefinition.setInstanceSupplier(getRedisTemplateInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'stringRedisTemplate'.
   */
  private static BeanInstanceSupplier<StringRedisTemplate> getStringRedisTemplateInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<StringRedisTemplate>forFactoryMethod(RedissonAutoConfigurationV4.class, "stringRedisTemplate", RedisConnectionFactory.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.redisson.spring.starter.RedissonAutoConfigurationV4", RedissonAutoConfigurationV4.class).stringRedisTemplate(args.get(0)));
  }

  /**
   * Get the bean definition for 'stringRedisTemplate'.
   */
  public static BeanDefinition getStringRedisTemplateBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(StringRedisTemplate.class);
    beanDefinition.setFactoryBeanName("org.redisson.spring.starter.RedissonAutoConfigurationV4");
    beanDefinition.setInstanceSupplier(getStringRedisTemplateInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'redissonConnectionFactory'.
   */
  private static BeanInstanceSupplier<RedissonConnectionFactory> getRedissonConnectionFactoryInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<RedissonConnectionFactory>forFactoryMethod(RedissonAutoConfigurationV4.class, "redissonConnectionFactory", RedissonClient.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.redisson.spring.starter.RedissonAutoConfigurationV4", RedissonAutoConfigurationV4.class).redissonConnectionFactory(args.get(0)));
  }

  /**
   * Get the bean definition for 'redissonConnectionFactory'.
   */
  public static BeanDefinition getRedissonConnectionFactoryBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(RedissonConnectionFactory.class);
    beanDefinition.setFactoryBeanName("org.redisson.spring.starter.RedissonAutoConfigurationV4");
    beanDefinition.setInstanceSupplier(getRedissonConnectionFactoryInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'redissonReactive'.
   */
  private static BeanInstanceSupplier<RedissonReactiveClient> getRedissonReactiveInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<RedissonReactiveClient>forFactoryMethod(RedissonAutoConfigurationV4.class, "redissonReactive", RedissonClient.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.redisson.spring.starter.RedissonAutoConfigurationV4", RedissonAutoConfigurationV4.class).redissonReactive(args.get(0)));
  }

  /**
   * Get the bean definition for 'redissonReactive'.
   */
  public static BeanDefinition getRedissonReactiveBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(RedissonReactiveClient.class);
    beanDefinition.setLazyInit(true);
    beanDefinition.setDestroyMethodNames("shutdown");
    beanDefinition.setFactoryBeanName("org.redisson.spring.starter.RedissonAutoConfigurationV4");
    beanDefinition.setInstanceSupplier(getRedissonReactiveInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'redissonRxJava'.
   */
  private static BeanInstanceSupplier<RedissonRxClient> getRedissonRxJavaInstanceSupplier() {
    return BeanInstanceSupplier.<RedissonRxClient>forFactoryMethod(RedissonAutoConfigurationV4.class, "redissonRxJava", RedissonClient.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.redisson.spring.starter.RedissonAutoConfigurationV4", RedissonAutoConfigurationV4.class).redissonRxJava(args.get(0)));
  }

  /**
   * Get the bean definition for 'redissonRxJava'.
   */
  public static BeanDefinition getRedissonRxJavaBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(RedissonRxClient.class);
    beanDefinition.setLazyInit(true);
    beanDefinition.setDestroyMethodNames("shutdown");
    beanDefinition.setFactoryBeanName("org.redisson.spring.starter.RedissonAutoConfigurationV4");
    beanDefinition.setInstanceSupplier(getRedissonRxJavaInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'redisson'.
   */
  private static BeanInstanceSupplier<RedissonClient> getRedissonInstanceSupplier() {
    return BeanInstanceSupplier.<RedissonClient>forFactoryMethod(RedissonAutoConfigurationV4.class, "redisson")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean("org.redisson.spring.starter.RedissonAutoConfigurationV4", RedissonAutoConfigurationV4.class).redisson());
  }

  /**
   * Get the bean definition for 'redisson'.
   */
  public static BeanDefinition getRedissonBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(RedissonClient.class);
    beanDefinition.setDestroyMethodNames("shutdown");
    beanDefinition.setFactoryBeanName("org.redisson.spring.starter.RedissonAutoConfigurationV4");
    beanDefinition.setInstanceSupplier(getRedissonInstanceSupplier());
    return beanDefinition;
  }
}
