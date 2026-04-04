package org.springframework.ai.model.openai.autoconfigure;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link OpenAiConnectionProperties}.
 */
@Generated
public class OpenAiConnectionProperties__BeanDefinitions {
  /**
   * Get the bean definition for 'openAiConnectionProperties'.
   */
  public static BeanDefinition getOpenAiConnectionPropertiesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(OpenAiConnectionProperties.class);
    beanDefinition.setInstanceSupplier(OpenAiConnectionProperties::new);
    return beanDefinition;
  }
}
