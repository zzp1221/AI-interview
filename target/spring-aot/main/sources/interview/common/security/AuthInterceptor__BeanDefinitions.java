package interview.common.security;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link AuthInterceptor}.
 */
@Generated
public class AuthInterceptor__BeanDefinitions {
  /**
   * Get the bean definition for 'authInterceptor'.
   */
  public static BeanDefinition getAuthInterceptorBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(AuthInterceptor.class);
    beanDefinition.setInstanceSupplier(AuthInterceptor::new);
    return beanDefinition;
  }
}
