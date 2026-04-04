package org.springframework.ai.vectorstore.pgvector.autoconfigure;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link PgVectorStoreProperties}.
 */
@Generated
public class PgVectorStoreProperties__BeanDefinitions {
  /**
   * Get the bean definition for 'pgVectorStoreProperties'.
   */
  public static BeanDefinition getPgVectorStorePropertiesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(PgVectorStoreProperties.class);
    beanDefinition.setInstanceSupplier(PgVectorStoreProperties::new);
    return beanDefinition;
  }
}
