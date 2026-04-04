package org.springframework.ai.model.chat.client.autoconfigure;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ChatClientAutoConfiguration}.
 */
@Generated
public class ChatClientAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'chatClientAutoConfiguration'.
   */
  public static BeanDefinition getChatClientAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ChatClientAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(ChatClientAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'chatClientBuilderConfigurer'.
   */
  private static BeanInstanceSupplier<ChatClientBuilderConfigurer> getChatClientBuilderConfigurerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ChatClientBuilderConfigurer>forFactoryMethod(ChatClientAutoConfiguration.class, "chatClientBuilderConfigurer", ObjectProvider.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.ai.model.chat.client.autoconfigure.ChatClientAutoConfiguration", ChatClientAutoConfiguration.class).chatClientBuilderConfigurer(args.get(0)));
  }

  /**
   * Get the bean definition for 'chatClientBuilderConfigurer'.
   */
  public static BeanDefinition getChatClientBuilderConfigurerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ChatClientBuilderConfigurer.class);
    beanDefinition.setFactoryBeanName("org.springframework.ai.model.chat.client.autoconfigure.ChatClientAutoConfiguration");
    beanDefinition.setInstanceSupplier(getChatClientBuilderConfigurerInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'chatClientBuilder'.
   */
  private static BeanInstanceSupplier<ChatClient.Builder> getChatClientBuilderInstanceSupplier() {
    return BeanInstanceSupplier.<ChatClient.Builder>forFactoryMethod(ChatClientAutoConfiguration.class, "chatClientBuilder", ChatClientBuilderConfigurer.class, ChatModel.class, ObjectProvider.class, ObjectProvider.class, ObjectProvider.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.ai.model.chat.client.autoconfigure.ChatClientAutoConfiguration", ChatClientAutoConfiguration.class).chatClientBuilder(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4)));
  }

  /**
   * Get the bean definition for 'chatClientBuilder'.
   */
  public static BeanDefinition getChatClientBuilderBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ChatClient.Builder.class);
    beanDefinition.setScope("prototype");
    beanDefinition.setFactoryBeanName("org.springframework.ai.model.chat.client.autoconfigure.ChatClientAutoConfiguration");
    beanDefinition.setInstanceSupplier(getChatClientBuilderInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Bean definitions for {@link ChatClientAutoConfiguration.TracerNotPresentObservationConfiguration}.
   */
  @Generated
  public static class TracerNotPresentObservationConfiguration {
    /**
     * Get the bean definition for 'tracerNotPresentObservationConfiguration'.
     */
    public static BeanDefinition getTracerNotPresentObservationConfigurationBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(ChatClientAutoConfiguration.TracerNotPresentObservationConfiguration.class);
      beanDefinition.setInstanceSupplier(ChatClientAutoConfiguration.TracerNotPresentObservationConfiguration::new);
      return beanDefinition;
    }
  }
}
