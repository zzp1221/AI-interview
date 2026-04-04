package org.springframework.boot.http.client.autoconfigure.reactive;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ReactiveHttpClientsProperties}.
 */
@Generated
public class ReactiveHttpClientsProperties__BeanDefinitions {
  /**
   * Get the bean definition for 'reactiveHttpClientsProperties'.
   */
  public static BeanDefinition getReactiveHttpClientsPropertiesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ReactiveHttpClientsProperties.class);
    beanDefinition.setInstanceSupplier(ReactiveHttpClientsProperties::new);
    return beanDefinition;
  }
}
