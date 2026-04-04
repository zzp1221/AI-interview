package org.springframework.ai.model.tool.autoconfigure;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ToolCallingProperties}.
 */
@Generated
public class ToolCallingProperties__BeanDefinitions {
  /**
   * Get the bean definition for 'toolCallingProperties'.
   */
  public static BeanDefinition getToolCallingPropertiesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ToolCallingProperties.class);
    beanDefinition.setInstanceSupplier(ToolCallingProperties::new);
    return beanDefinition;
  }
}
