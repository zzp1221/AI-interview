package org.springframework.ai.vectorstore.observation.autoconfigure;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link VectorStoreObservationProperties}.
 */
@Generated
public class VectorStoreObservationProperties__BeanDefinitions {
  /**
   * Get the bean definition for 'vectorStoreObservationProperties'.
   */
  public static BeanDefinition getVectorStoreObservationPropertiesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(VectorStoreObservationProperties.class);
    beanDefinition.setInstanceSupplier(VectorStoreObservationProperties::new);
    return beanDefinition;
  }
}
