package interview.infrastructure.redis;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import tools.jackson.databind.ObjectMapper;

/**
 * Bean definitions for {@link InterviewSessionCache}.
 */
@Generated
public class InterviewSessionCache__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'interviewSessionCache'.
   */
  private static BeanInstanceSupplier<InterviewSessionCache> getInterviewSessionCacheInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<InterviewSessionCache>forConstructor(RedisService.class, ObjectMapper.class)
            .withGenerator((registeredBean, args) -> new InterviewSessionCache(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'interviewSessionCache'.
   */
  public static BeanDefinition getInterviewSessionCacheBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(InterviewSessionCache.class);
    beanDefinition.setInstanceSupplier(getInterviewSessionCacheInstanceSupplier());
    return beanDefinition;
  }
}
