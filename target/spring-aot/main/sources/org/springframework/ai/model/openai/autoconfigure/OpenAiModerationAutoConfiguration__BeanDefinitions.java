package org.springframework.ai.model.openai.autoconfigure;

import org.springframework.ai.openai.OpenAiModerationModel;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.retry.RetryTemplate;
import org.springframework.web.client.ResponseErrorHandler;

/**
 * Bean definitions for {@link OpenAiModerationAutoConfiguration}.
 */
@Generated
public class OpenAiModerationAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'openAiModerationAutoConfiguration'.
   */
  public static BeanDefinition getOpenAiModerationAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(OpenAiModerationAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(OpenAiModerationAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'openAiModerationModel'.
   */
  private static BeanInstanceSupplier<OpenAiModerationModel> getOpenAiModerationModelInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<OpenAiModerationModel>forFactoryMethod(OpenAiModerationAutoConfiguration.class, "openAiModerationModel", OpenAiConnectionProperties.class, OpenAiModerationProperties.class, RetryTemplate.class, ObjectProvider.class, ResponseErrorHandler.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.ai.model.openai.autoconfigure.OpenAiModerationAutoConfiguration", OpenAiModerationAutoConfiguration.class).openAiModerationModel(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4)));
  }

  /**
   * Get the bean definition for 'openAiModerationModel'.
   */
  public static BeanDefinition getOpenAiModerationModelBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(OpenAiModerationModel.class);
    beanDefinition.setFactoryBeanName("org.springframework.ai.model.openai.autoconfigure.OpenAiModerationAutoConfiguration");
    beanDefinition.setInstanceSupplier(getOpenAiModerationModelInstanceSupplier());
    return beanDefinition;
  }
}
