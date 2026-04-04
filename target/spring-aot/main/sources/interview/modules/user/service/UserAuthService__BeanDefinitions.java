package interview.modules.user.service;

import interview.infrastructure.mapper.KnowledgeBaseMapper;
import interview.modules.knowledgebase.repository.KnowledgeBaseRepository;
import interview.modules.user.repository.UserRepository;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link UserAuthService}.
 */
@Generated
public class UserAuthService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'userAuthService'.
   */
  private static BeanInstanceSupplier<UserAuthService> getUserAuthServiceInstanceSupplier() {
    return BeanInstanceSupplier.<UserAuthService>forConstructor(UserRepository.class, KnowledgeBaseRepository.class, KnowledgeBaseMapper.class)
            .withGenerator((registeredBean, args) -> new UserAuthService(args.get(0), args.get(1), args.get(2)));
  }

  /**
   * Get the bean definition for 'userAuthService'.
   */
  public static BeanDefinition getUserAuthServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(UserAuthService.class);
    beanDefinition.setInstanceSupplier(getUserAuthServiceInstanceSupplier());
    return beanDefinition;
  }
}
