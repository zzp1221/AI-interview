package org.springframework.ai.retry.autoconfigure;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.retry.RetryTemplate;
import org.springframework.web.client.ResponseErrorHandler;

/**
 * Bean definitions for {@link SpringAiRetryAutoConfiguration}.
 */
@Generated
public class SpringAiRetryAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'springAiRetryAutoConfiguration'.
   */
  public static BeanDefinition getSpringAiRetryAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(SpringAiRetryAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(SpringAiRetryAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'retryTemplate'.
   */
  private static BeanInstanceSupplier<RetryTemplate> getRetryTemplateInstanceSupplier() {
    return BeanInstanceSupplier.<RetryTemplate>forFactoryMethod(SpringAiRetryAutoConfiguration.class, "retryTemplate", SpringAiRetryProperties.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.ai.retry.autoconfigure.SpringAiRetryAutoConfiguration", SpringAiRetryAutoConfiguration.class).retryTemplate(args.get(0)));
  }

  /**
   * Get the bean definition for 'retryTemplate'.
   */
  public static BeanDefinition getRetryTemplateBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(RetryTemplate.class);
    beanDefinition.setFactoryBeanName("org.springframework.ai.retry.autoconfigure.SpringAiRetryAutoConfiguration");
    beanDefinition.setInstanceSupplier(getRetryTemplateInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'responseErrorHandler'.
   */
  private static BeanInstanceSupplier<ResponseErrorHandler> getResponseErrorHandlerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ResponseErrorHandler>forFactoryMethod(SpringAiRetryAutoConfiguration.class, "responseErrorHandler", SpringAiRetryProperties.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.ai.retry.autoconfigure.SpringAiRetryAutoConfiguration", SpringAiRetryAutoConfiguration.class).responseErrorHandler(args.get(0)));
  }

  /**
   * Get the bean definition for 'responseErrorHandler'.
   */
  public static BeanDefinition getResponseErrorHandlerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ResponseErrorHandler.class);
    beanDefinition.setFactoryBeanName("org.springframework.ai.retry.autoconfigure.SpringAiRetryAutoConfiguration");
    beanDefinition.setInstanceSupplier(getResponseErrorHandlerInstanceSupplier());
    return beanDefinition;
  }
}
