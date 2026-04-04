package org.springframework.ai.model.openai.autoconfigure;

import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.retry.RetryTemplate;
import org.springframework.web.client.ResponseErrorHandler;

/**
 * Bean definitions for {@link OpenAiAudioSpeechAutoConfiguration}.
 */
@Generated
public class OpenAiAudioSpeechAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'openAiAudioSpeechAutoConfiguration'.
   */
  public static BeanDefinition getOpenAiAudioSpeechAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(OpenAiAudioSpeechAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(OpenAiAudioSpeechAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'openAiAudioSpeechModel'.
   */
  private static BeanInstanceSupplier<OpenAiAudioSpeechModel> getOpenAiAudioSpeechModelInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<OpenAiAudioSpeechModel>forFactoryMethod(OpenAiAudioSpeechAutoConfiguration.class, "openAiAudioSpeechModel", OpenAiConnectionProperties.class, OpenAiAudioSpeechProperties.class, RetryTemplate.class, ObjectProvider.class, ObjectProvider.class, ResponseErrorHandler.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.ai.model.openai.autoconfigure.OpenAiAudioSpeechAutoConfiguration", OpenAiAudioSpeechAutoConfiguration.class).openAiAudioSpeechModel(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4), args.get(5)));
  }

  /**
   * Get the bean definition for 'openAiAudioSpeechModel'.
   */
  public static BeanDefinition getOpenAiAudioSpeechModelBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(OpenAiAudioSpeechModel.class);
    beanDefinition.setFactoryBeanName("org.springframework.ai.model.openai.autoconfigure.OpenAiAudioSpeechAutoConfiguration");
    beanDefinition.setInstanceSupplier(getOpenAiAudioSpeechModelInstanceSupplier());
    return beanDefinition;
  }
}
