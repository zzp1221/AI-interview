package interview.infrastructure.file;

import interview.common.config.StorageConfigProperties;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import software.amazon.awssdk.services.s3.S3Client;

/**
 * Bean definitions for {@link FileStorageService}.
 */
@Generated
public class FileStorageService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'fileStorageService'.
   */
  private static BeanInstanceSupplier<FileStorageService> getFileStorageServiceInstanceSupplier() {
    return BeanInstanceSupplier.<FileStorageService>forConstructor(S3Client.class, StorageConfigProperties.class)
            .withGenerator((registeredBean, args) -> new FileStorageService(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'fileStorageService'.
   */
  public static BeanDefinition getFileStorageServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(FileStorageService.class);
    beanDefinition.setInstanceSupplier(getFileStorageServiceInstanceSupplier());
    return beanDefinition;
  }
}
