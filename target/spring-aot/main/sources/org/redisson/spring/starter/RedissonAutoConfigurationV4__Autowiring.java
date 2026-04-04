package org.redisson.spring.starter;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link RedissonAutoConfigurationV4}.
 */
@Generated
public class RedissonAutoConfigurationV4__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static RedissonAutoConfigurationV4 apply(RegisteredBean registeredBean,
      RedissonAutoConfigurationV4 instance) {
    AutowiredFieldValueResolver.forField("redissonAutoConfigurationCustomizers").resolveAndSet(registeredBean, instance);
    AutowiredFieldValueResolver.forRequiredField("redissonProperties").resolveAndSet(registeredBean, instance);
    AutowiredFieldValueResolver.forRequiredField("redisProperties").resolveAndSet(registeredBean, instance);
    AutowiredFieldValueResolver.forRequiredField("ctx").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
