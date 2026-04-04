package org.springframework.ai.model.tool.autoconfigure;

import java.util.List;
import org.springframework.ai.model.tool.ToolCallingManager;
import org.springframework.ai.tool.execution.ToolExecutionExceptionProcessor;
import org.springframework.ai.tool.resolution.ToolCallbackResolver;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.GenericApplicationContext;

/**
 * Bean definitions for {@link ToolCallingAutoConfiguration}.
 */
@Generated
public class ToolCallingAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'toolCallingAutoConfiguration'.
   */
  public static BeanDefinition getToolCallingAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ToolCallingAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(ToolCallingAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'toolCallbackResolver'.
   */
  private static BeanInstanceSupplier<ToolCallbackResolver> getToolCallbackResolverInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ToolCallbackResolver>forFactoryMethod(ToolCallingAutoConfiguration.class, "toolCallbackResolver", GenericApplicationContext.class, List.class, ObjectProvider.class, ObjectProvider.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.ai.model.tool.autoconfigure.ToolCallingAutoConfiguration", ToolCallingAutoConfiguration.class).toolCallbackResolver(args.get(0), args.get(1), args.get(2), args.get(3)));
  }

  /**
   * Get the bean definition for 'toolCallbackResolver'.
   */
  public static BeanDefinition getToolCallbackResolverBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ToolCallbackResolver.class);
    beanDefinition.setFactoryBeanName("org.springframework.ai.model.tool.autoconfigure.ToolCallingAutoConfiguration");
    beanDefinition.setInstanceSupplier(getToolCallbackResolverInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'toolExecutionExceptionProcessor'.
   */
  private static BeanInstanceSupplier<ToolExecutionExceptionProcessor> getToolExecutionExceptionProcessorInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ToolExecutionExceptionProcessor>forFactoryMethod(ToolCallingAutoConfiguration.class, "toolExecutionExceptionProcessor", ToolCallingProperties.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.ai.model.tool.autoconfigure.ToolCallingAutoConfiguration", ToolCallingAutoConfiguration.class).toolExecutionExceptionProcessor(args.get(0)));
  }

  /**
   * Get the bean definition for 'toolExecutionExceptionProcessor'.
   */
  public static BeanDefinition getToolExecutionExceptionProcessorBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ToolExecutionExceptionProcessor.class);
    beanDefinition.setFactoryBeanName("org.springframework.ai.model.tool.autoconfigure.ToolCallingAutoConfiguration");
    beanDefinition.setInstanceSupplier(getToolExecutionExceptionProcessorInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'toolCallingManager'.
   */
  private static BeanInstanceSupplier<ToolCallingManager> getToolCallingManagerInstanceSupplier() {
    return BeanInstanceSupplier.<ToolCallingManager>forFactoryMethod(ToolCallingAutoConfiguration.class, "toolCallingManager", ToolCallbackResolver.class, ToolExecutionExceptionProcessor.class, ObjectProvider.class, ObjectProvider.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.ai.model.tool.autoconfigure.ToolCallingAutoConfiguration", ToolCallingAutoConfiguration.class).toolCallingManager(args.get(0), args.get(1), args.get(2), args.get(3)));
  }

  /**
   * Get the bean definition for 'toolCallingManager'.
   */
  public static BeanDefinition getToolCallingManagerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ToolCallingManager.class);
    beanDefinition.setFactoryBeanName("org.springframework.ai.model.tool.autoconfigure.ToolCallingAutoConfiguration");
    beanDefinition.setInstanceSupplier(getToolCallingManagerInstanceSupplier());
    return beanDefinition;
  }
}
