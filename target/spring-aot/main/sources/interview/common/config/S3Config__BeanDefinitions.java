package interview.common.config;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ConfigurationClassUtils;
import software.amazon.awssdk.services.s3.S3Client;

/**
 * Bean definitions for {@link S3Config}.
 */
@Generated
public class S3Config__BeanDefinitions {
  /**
   * Get the bean instance supplier for 's3Config'.
   */
  private static BeanInstanceSupplier<S3Config> getSConfigInstanceSupplier() {
    return BeanInstanceSupplier.<S3Config>forConstructor(StorageConfigProperties.class)
            .withGenerator((registeredBean, args) -> new S3Config$$SpringCGLIB$$0(args.get(0)));
  }

  /**
   * Get the bean definition for 's3Config'.
   */
  public static BeanDefinition getSConfigBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(S3Config.class);
    beanDefinition.setTargetType(S3Config.class);
    ConfigurationClassUtils.initializeConfigurationClass(S3Config.class);
    beanDefinition.setInstanceSupplier(getSConfigInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 's3Client'.
   */
  private static BeanInstanceSupplier<S3Client> getSClientInstanceSupplier() {
    return BeanInstanceSupplier.<S3Client>forFactoryMethod(S3Config$$SpringCGLIB$$0.class, "s3Client")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean("s3Config", S3Config.class).s3Client());
  }

  /**
   * Get the bean definition for 's3Client'.
   */
  public static BeanDefinition getSClientBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(S3Client.class);
    beanDefinition.setDestroyMethodNames("close");
    beanDefinition.setFactoryBeanName("s3Config");
    beanDefinition.setInstanceSupplier(getSClientInstanceSupplier());
    return beanDefinition;
  }
}
