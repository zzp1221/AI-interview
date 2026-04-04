package org.springframework.ai.model.openai.autoconfigure;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link OpenAiEmbeddingProperties}.
 */
@Generated
public class OpenAiEmbeddingProperties__BeanDefinitions {
  /**
   * Get the bean definition for 'openAiEmbeddingProperties'.
   */
  public static BeanDefinition getOpenAiEmbeddingPropertiesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(OpenAiEmbeddingProperties.class);
    beanDefinition.setInstanceSupplier(OpenAiEmbeddingProperties::new);
    return beanDefinition;
  }
}
