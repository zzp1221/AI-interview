package interview.modules.user;

import interview.modules.user.service.UserAuthService;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link UserAuthController}.
 */
@Generated
public class UserAuthController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'userAuthController'.
   */
  private static BeanInstanceSupplier<UserAuthController> getUserAuthControllerInstanceSupplier() {
    return BeanInstanceSupplier.<UserAuthController>forConstructor(UserAuthService.class)
            .withGenerator((registeredBean, args) -> new UserAuthController(args.get(0)));
  }

  /**
   * Get the bean definition for 'userAuthController'.
   */
  public static BeanDefinition getUserAuthControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(UserAuthController.class);
    beanDefinition.setInstanceSupplier(getUserAuthControllerInstanceSupplier());
    return beanDefinition;
  }
}
