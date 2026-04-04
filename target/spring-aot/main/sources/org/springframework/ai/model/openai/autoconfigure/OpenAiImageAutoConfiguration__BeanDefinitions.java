package org.springframework.ai.model.openai.autoconfigure;

import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.retry.RetryTemplate;
import org.springframework.web.client.ResponseErrorHandler;

/**
 * Bean definitions for {@link OpenAiImageAutoConfiguration}.
 */
@Generated
public class OpenAiImageAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'openAiImageAutoConfiguration'.
   */
  public static BeanDefinition getOpenAiImageAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(OpenAiImageAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(OpenAiImageAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'openAiImageModel'.
   */
  private static BeanInstanceSupplier<OpenAiImageModel> getOpenAiImageModelInstanceSupplier() {
    return BeanInstanceSupplier.<OpenAiImageModel>forFactoryMethod(OpenAiImageAutoConfiguration.class, "openAiImageModel", OpenAiConnectionProperties.class, OpenAiImageProperties.class, ObjectProvider.class, RetryTemplate.class, ResponseErrorHandler.class, ObjectProvider.class, ObjectProvider.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.ai.model.openai.autoconfigure.OpenAiImageAutoConfiguration", OpenAiImageAutoConfiguration.class).openAiImageModel(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4), args.get(5), args.get(6)));
  }

  /**
   * Get the bean definition for 'openAiImageModel'.
   */
  public static BeanDefinition getOpenAiImageModelBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(OpenAiImageModel.class);
    beanDefinition.setFactoryBeanName("org.springframework.ai.model.openai.autoconfigure.OpenAiImageAutoConfiguration");
    beanDefinition.setInstanceSupplier(getOpenAiImageModelInstanceSupplier());
    return beanDefinition;
  }
}
