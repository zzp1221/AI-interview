package org.springframework.ai.model.openai.autoconfigure;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link OpenAiChatProperties}.
 */
@Generated
public class OpenAiChatProperties__BeanDefinitions {
  /**
   * Get the bean definition for 'openAiChatProperties'.
   */
  public static BeanDefinition getOpenAiChatPropertiesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(OpenAiChatProperties.class);
    beanDefinition.setInstanceSupplier(OpenAiChatProperties::new);
    return beanDefinition;
  }
}
