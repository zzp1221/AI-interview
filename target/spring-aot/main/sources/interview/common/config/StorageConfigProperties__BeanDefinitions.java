package interview.common.config;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link StorageConfigProperties}.
 */
@Generated
public class StorageConfigProperties__BeanDefinitions {
  /**
   * Get the bean definition for 'storageConfigProperties'.
   */
  public static BeanDefinition getStorageConfigPropertiesBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(StorageConfigProperties.class);
    beanDefinition.setInstanceSupplier(StorageConfigProperties::new);
    return beanDefinition;
  }
}
