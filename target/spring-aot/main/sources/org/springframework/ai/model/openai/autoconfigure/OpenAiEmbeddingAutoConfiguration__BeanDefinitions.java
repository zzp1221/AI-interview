package org.springframework.ai.model.openai.autoconfigure;

import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.retry.RetryTemplate;
import org.springframework.web.client.ResponseErrorHandler;

/**
 * Bean definitions for {@link OpenAiEmbeddingAutoConfiguration}.
 */
@Generated
public class OpenAiEmbeddingAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'openAiEmbeddingAutoConfiguration'.
   */
  public static BeanDefinition getOpenAiEmbeddingAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(OpenAiEmbeddingAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(OpenAiEmbeddingAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'openAiEmbeddingModel'.
   */
  private static BeanInstanceSupplier<OpenAiEmbeddingModel> getOpenAiEmbeddingModelInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<OpenAiEmbeddingModel>forFactoryMethod(OpenAiEmbeddingAutoConfiguration.class, "openAiEmbeddingModel", OpenAiConnectionProperties.class, OpenAiEmbeddingProperties.class, ObjectProvider.class, ObjectProvider.class, RetryTemplate.class, ResponseErrorHandler.class, ObjectProvider.class, ObjectProvider.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.ai.model.openai.autoconfigure.OpenAiEmbeddingAutoConfiguration", OpenAiEmbeddingAutoConfiguration.class).openAiEmbeddingModel(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4), args.get(5), args.get(6), args.get(7)));
  }

  /**
   * Get the bean definition for 'openAiEmbeddingModel'.
   */
  public static BeanDefinition getOpenAiEmbeddingModelBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(OpenAiEmbeddingModel.class);
    beanDefinition.setFactoryBeanName("org.springframework.ai.model.openai.autoconfigure.OpenAiEmbeddingAutoConfiguration");
    beanDefinition.setInstanceSupplier(getOpenAiEmbeddingModelInstanceSupplier());
    return beanDefinition;
  }
}
