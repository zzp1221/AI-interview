package interview.common.aspect;

import org.redisson.api.RedissonClient;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link RateLimitAspect}.
 */
@Generated
public class RateLimitAspect__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'rateLimitAspect'.
   */
  private static BeanInstanceSupplier<RateLimitAspect> getRateLimitAspectInstanceSupplier() {
    return BeanInstanceSupplier.<RateLimitAspect>forConstructor(RedissonClient.class)
            .withGenerator((registeredBean, args) -> new RateLimitAspect(args.get(0)));
  }

  /**
   * Get the bean definition for 'rateLimitAspect'.
   */
  public static BeanDefinition getRateLimitAspectBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(RateLimitAspect.class);
    beanDefinition.setInitMethodNames("init");
    beanDefinition.setInstanceSupplier(getRateLimitAspectInstanceSupplier());
    return beanDefinition;
  }
}
