package interview.common.security;

import java.lang.String;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link AuthTokenService}.
 */
@Generated
public class AuthTokenService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'authTokenService'.
   */
  private static BeanInstanceSupplier<AuthTokenService> getAuthTokenServiceInstanceSupplier() {
    return BeanInstanceSupplier.<AuthTokenService>forConstructor(String.class, long.class)
            .withGenerator((registeredBean, args) -> new AuthTokenService(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'authTokenService'.
   */
  public static BeanDefinition getAuthTokenServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(AuthTokenService.class);
    beanDefinition.setInstanceSupplier(getAuthTokenServiceInstanceSupplier());
    return beanDefinition;
  }
}
