package interview.infrastructure.mapper;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link RagChatMapperImpl}.
 */
@Generated
public class RagChatMapperImpl__BeanDefinitions {
  /**
   * Get the bean definition for 'ragChatMapperImpl'.
   */
  public static BeanDefinition getRagChatMapperImplBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(RagChatMapperImpl.class);
    beanDefinition.setInstanceSupplier(RagChatMapperImpl::new);
    return beanDefinition;
  }
}
