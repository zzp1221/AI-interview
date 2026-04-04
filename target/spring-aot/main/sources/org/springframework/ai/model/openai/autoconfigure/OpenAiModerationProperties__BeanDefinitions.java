package org.springframework.ai.model.openai.autoconfigure;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link OpenAiModerationProperties}.
 */
@Generated
public class OpenAiModerationProperties__BeanDefinitions {
  /**
   * Get the bean definition for 'openAiModerationProperties'.
   */
  public static BeanDefinition getOpenAiModerationPropertiesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(OpenAiModerationProperties.class);
    beanDefinition.setInstanceSupplier(OpenAiModerationProperties::new);
    return beanDefinition;
  }
}
