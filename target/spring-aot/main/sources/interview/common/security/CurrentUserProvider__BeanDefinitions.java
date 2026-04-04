package interview.common.security;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link CurrentUserProvider}.
 */
@Generated
public class CurrentUserProvider__BeanDefinitions {
  /**
   * Get the bean definition for 'currentUserProvider'.
   */
  public static BeanDefinition getCurrentUserProviderBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(CurrentUserProvider.class);
    beanDefinition.setInstanceSupplier(CurrentUserProvider::new);
    return beanDefinition;
  }
}
