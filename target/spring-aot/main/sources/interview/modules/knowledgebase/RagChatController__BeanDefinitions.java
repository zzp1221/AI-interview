package interview.modules.knowledgebase;

import interview.common.security.CurrentUserProvider;
import interview.modules.knowledgebase.service.RagChatSessionService;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link RagChatController}.
 */
@Generated
public class RagChatController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'ragChatController'.
   */
  private static BeanInstanceSupplier<RagChatController> getRagChatControllerInstanceSupplier() {
    return BeanInstanceSupplier.<RagChatController>forConstructor(RagChatSessionService.class, CurrentUserProvider.class)
            .withGenerator((registeredBean, args) -> new RagChatController(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'ragChatController'.
   */
  public static BeanDefinition getRagChatControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(RagChatController.class);
    beanDefinition.setInstanceSupplier(getRagChatControllerInstanceSupplier());
    return beanDefinition;
  }
}
