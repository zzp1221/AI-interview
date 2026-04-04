package org.springframework.ai.model.image.observation.autoconfigure;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ImageObservationAutoConfiguration}.
 */
@Generated
public class ImageObservationAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'imageObservationAutoConfiguration'.
   */
  public static BeanDefinition getImageObservationAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ImageObservationAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(ImageObservationAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Bean definitions for {@link ImageObservationAutoConfiguration.TracerNotPresentObservationConfiguration}.
   */
  @Generated
  public static class TracerNotPresentObservationConfiguration {
    /**
     * Get the bean definition for 'tracerNotPresentObservationConfiguration'.
     */
    public static BeanDefinition getTracerNotPresentObservationConfigurationBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(ImageObservationAutoConfiguration.TracerNotPresentObservationConfiguration.class);
      beanDefinition.setInstanceSupplier(ImageObservationAutoConfiguration.TracerNotPresentObservationConfiguration::new);
      return beanDefinition;
    }
  }
}
