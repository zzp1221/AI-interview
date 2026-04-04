package org.springframework.ai.model.embedding.observation.autoconfigure;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link EmbeddingObservationAutoConfiguration}.
 */
@Generated
public class EmbeddingObservationAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'embeddingObservationAutoConfiguration'.
   */
  public static BeanDefinition getEmbeddingObservationAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(EmbeddingObservationAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(EmbeddingObservationAutoConfiguration::new);
    return beanDefinition;
  }
}
