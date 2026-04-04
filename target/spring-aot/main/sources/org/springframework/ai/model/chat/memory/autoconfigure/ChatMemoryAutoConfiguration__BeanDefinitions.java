package org.springframework.ai.model.chat.memory.autoconfigure;

import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ChatMemoryAutoConfiguration}.
 */
@Generated
public class ChatMemoryAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'chatMemoryAutoConfiguration'.
   */
  public static BeanDefinition getChatMemoryAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ChatMemoryAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(ChatMemoryAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'chatMemoryRepository'.
   */
  private static BeanInstanceSupplier<ChatMemoryRepository> getChatMemoryRepositoryInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ChatMemoryRepository>forFactoryMethod(ChatMemoryAutoConfiguration.class, "chatMemoryRepository")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean("org.springframework.ai.model.chat.memory.autoconfigure.ChatMemoryAutoConfiguration", ChatMemoryAutoConfiguration.class).chatMemoryRepository());
  }

  /**
   * Get the bean definition for 'chatMemoryRepository'.
   */
  public static BeanDefinition getChatMemoryRepositoryBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ChatMemoryRepository.class);
    beanDefinition.setFactoryBeanName("org.springframework.ai.model.chat.memory.autoconfigure.ChatMemoryAutoConfiguration");
    beanDefinition.setInstanceSupplier(getChatMemoryRepositoryInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'chatMemory'.
   */
  private static BeanInstanceSupplier<ChatMemory> getChatMemoryInstanceSupplier() {
    return BeanInstanceSupplier.<ChatMemory>forFactoryMethod(ChatMemoryAutoConfiguration.class, "chatMemory", ChatMemoryRepository.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.ai.model.chat.memory.autoconfigure.ChatMemoryAutoConfiguration", ChatMemoryAutoConfiguration.class).chatMemory(args.get(0)));
  }

  /**
   * Get the bean definition for 'chatMemory'.
   */
  public static BeanDefinition getChatMemoryBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ChatMemory.class);
    beanDefinition.setFactoryBeanName("org.springframework.ai.model.chat.memory.autoconfigure.ChatMemoryAutoConfiguration");
    beanDefinition.setInstanceSupplier(getChatMemoryInstanceSupplier());
    return beanDefinition;
  }
}
