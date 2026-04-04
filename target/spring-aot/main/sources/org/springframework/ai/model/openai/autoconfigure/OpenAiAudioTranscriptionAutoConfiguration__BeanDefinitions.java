package org.springframework.ai.model.openai.autoconfigure;

import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.retry.RetryTemplate;
import org.springframework.web.client.ResponseErrorHandler;

/**
 * Bean definitions for {@link OpenAiAudioTranscriptionAutoConfiguration}.
 */
@Generated
public class OpenAiAudioTranscriptionAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'openAiAudioTranscriptionAutoConfiguration'.
   */
  public static BeanDefinition getOpenAiAudioTranscriptionAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(OpenAiAudioTranscriptionAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(OpenAiAudioTranscriptionAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'openAiAudioTranscriptionModel'.
   */
  private static BeanInstanceSupplier<OpenAiAudioTranscriptionModel> getOpenAiAudioTranscriptionModelInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<OpenAiAudioTranscriptionModel>forFactoryMethod(OpenAiAudioTranscriptionAutoConfiguration.class, "openAiAudioTranscriptionModel", OpenAiConnectionProperties.class, OpenAiAudioTranscriptionProperties.class, RetryTemplate.class, ObjectProvider.class, ObjectProvider.class, ResponseErrorHandler.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.ai.model.openai.autoconfigure.OpenAiAudioTranscriptionAutoConfiguration", OpenAiAudioTranscriptionAutoConfiguration.class).openAiAudioTranscriptionModel(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4), args.get(5)));
  }

  /**
   * Get the bean definition for 'openAiAudioTranscriptionModel'.
   */
  public static BeanDefinition getOpenAiAudioTranscriptionModelBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(OpenAiAudioTranscriptionModel.class);
    beanDefinition.setFactoryBeanName("org.springframework.ai.model.openai.autoconfigure.OpenAiAudioTranscriptionAutoConfiguration");
    beanDefinition.setInstanceSupplier(getOpenAiAudioTranscriptionModelInstanceSupplier());
    return beanDefinition;
  }
}
