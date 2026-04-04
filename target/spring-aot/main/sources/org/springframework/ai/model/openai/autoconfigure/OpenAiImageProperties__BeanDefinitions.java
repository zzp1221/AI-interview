package org.springframework.ai.model.openai.autoconfigure;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link OpenAiImageProperties}.
 */
@Generated
public class OpenAiImageProperties__BeanDefinitions {
  /**
   * Get the bean definition for 'openAiImageProperties'.
   */
  public static BeanDefinition getOpenAiImagePropertiesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(OpenAiImageProperties.class);
    beanDefinition.setInstanceSupplier(OpenAiImageProperties::new);
    return beanDefinition;
  }
}
