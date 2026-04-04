package interview.infrastructure.redis;

import org.redisson.api.RedissonClient;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link RedisService}.
 */
@Generated
public class RedisService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'redisService'.
   */
  private static BeanInstanceSupplier<RedisService> getRedisServiceInstanceSupplier() {
    return BeanInstanceSupplier.<RedisService>forConstructor(RedissonClient.class)
            .withGenerator((registeredBean, args) -> new RedisService(args.get(0)));
  }

  /**
   * Get the bean definition for 'redisService'.
   */
  public static BeanDefinition getRedisServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(RedisService.class);
    beanDefinition.setInstanceSupplier(getRedisServiceInstanceSupplier());
    return beanDefinition;
  }
}
