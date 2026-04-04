package org.springframework.ai.model.openai.autoconfigure;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link OpenAiAudioTranscriptionProperties}.
 */
@Generated
public class OpenAiAudioTranscriptionProperties__BeanDefinitions {
  /**
   * Get the bean definition for 'openAiAudioTranscriptionProperties'.
   */
  public static BeanDefinition getOpenAiAudioTranscriptionPropertiesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(OpenAiAudioTranscriptionProperties.class);
    beanDefinition.setInstanceSupplier(OpenAiAudioTranscriptionProperties::new);
    return beanDefinition;
  }
}
