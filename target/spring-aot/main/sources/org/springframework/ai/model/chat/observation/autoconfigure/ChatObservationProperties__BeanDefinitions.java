package org.springframework.ai.model.chat.observation.autoconfigure;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ChatObservationProperties}.
 */
@Generated
public class ChatObservationProperties__BeanDefinitions {
  /**
   * Get the bean definition for 'chatObservationProperties'.
   */
  public static BeanDefinition getChatObservationPropertiesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ChatObservationProperties.class);
    beanDefinition.setInstanceSupplier(ChatObservationProperties::new);
    return beanDefinition;
  }
}
