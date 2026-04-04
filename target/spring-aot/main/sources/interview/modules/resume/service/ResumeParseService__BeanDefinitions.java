package interview.modules.resume.service;

import interview.infrastructure.file.ContentTypeDetectionService;
import interview.infrastructure.file.DocumentParseService;
import interview.infrastructure.file.FileStorageService;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ResumeParseService}.
 */
@Generated
public class ResumeParseService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'resumeParseService'.
   */
  private static BeanInstanceSupplier<ResumeParseService> getResumeParseServiceInstanceSupplier() {
    return BeanInstanceSupplier.<ResumeParseService>forConstructor(DocumentParseService.class, ContentTypeDetectionService.class, FileStorageService.class)
            .withGenerator((registeredBean, args) -> new ResumeParseService(args.get(0), args.get(1), args.get(2)));
  }

  /**
   * Get the bean definition for 'resumeParseService'.
   */
  public static BeanDefinition getResumeParseServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ResumeParseService.class);
    beanDefinition.setInstanceSupplier(getResumeParseServiceInstanceSupplier());
    return beanDefinition;
  }
}
