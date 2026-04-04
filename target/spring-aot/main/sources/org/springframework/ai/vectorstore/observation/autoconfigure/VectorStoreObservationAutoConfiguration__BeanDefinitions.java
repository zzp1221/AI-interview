package org.springframework.ai.vectorstore.observation.autoconfigure;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link VectorStoreObservationAutoConfiguration}.
 */
@Generated
public class VectorStoreObservationAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'vectorStoreObservationAutoConfiguration'.
   */
  public static BeanDefinition getVectorStoreObservationAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(VectorStoreObservationAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(VectorStoreObservationAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Bean definitions for {@link VectorStoreObservationAutoConfiguration.TracerNotPresentObservationConfiguration}.
   */
  @Generated
  public static class TracerNotPresentObservationConfiguration {
    /**
     * Get the bean definition for 'tracerNotPresentObservationConfiguration'.
     */
    public static BeanDefinition getTracerNotPresentObservationConfigurationBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(VectorStoreObservationAutoConfiguration.TracerNotPresentObservationConfiguration.class);
      beanDefinition.setInstanceSupplier(VectorStoreObservationAutoConfiguration.TracerNotPresentObservationConfiguration::new);
      return beanDefinition;
    }
  }
}
