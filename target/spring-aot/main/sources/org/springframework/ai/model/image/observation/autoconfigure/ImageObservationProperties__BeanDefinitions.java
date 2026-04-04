package org.springframework.ai.model.image.observation.autoconfigure;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ImageObservationProperties}.
 */
@Generated
public class ImageObservationProperties__BeanDefinitions {
  /**
   * Get the bean definition for 'imageObservationProperties'.
   */
  public static BeanDefinition getImageObservationPropertiesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ImageObservationProperties.class);
    beanDefinition.setInstanceSupplier(ImageObservationProperties::new);
    return beanDefinition;
  }
}
