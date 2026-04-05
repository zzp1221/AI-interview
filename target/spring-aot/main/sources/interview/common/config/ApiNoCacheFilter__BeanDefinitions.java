package interview.common.config;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ApiNoCacheFilter}.
 */
@Generated
public class ApiNoCacheFilter__BeanDefinitions {
  /**
   * Get the bean definition for 'apiNoCacheFilter'.
   */
  public static BeanDefinition getApiNoCacheFilterBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ApiNoCacheFilter.class);
    beanDefinition.setInstanceSupplier(ApiNoCacheFilter::new);
    return beanDefinition;
  }
}
