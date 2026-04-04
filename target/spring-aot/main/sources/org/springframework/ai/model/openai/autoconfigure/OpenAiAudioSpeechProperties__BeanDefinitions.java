package org.springframework.ai.model.openai.autoconfigure;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link OpenAiAudioSpeechProperties}.
 */
@Generated
public class OpenAiAudioSpeechProperties__BeanDefinitions {
  /**
   * Get the bean definition for 'openAiAudioSpeechProperties'.
   */
  public static BeanDefinition getOpenAiAudioSpeechPropertiesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(OpenAiAudioSpeechProperties.class);
    beanDefinition.setInstanceSupplier(OpenAiAudioSpeechProperties::new);
    return beanDefinition;
  }
}
