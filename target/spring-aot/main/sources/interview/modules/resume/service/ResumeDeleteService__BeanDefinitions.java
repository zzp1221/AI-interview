package interview.modules.resume.service;

import interview.infrastructure.file.FileStorageService;
import interview.modules.interview.service.InterviewPersistenceService;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ResumeDeleteService}.
 */
@Generated
public class ResumeDeleteService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'resumeDeleteService'.
   */
  private static BeanInstanceSupplier<ResumeDeleteService> getResumeDeleteServiceInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ResumeDeleteService>forConstructor(ResumePersistenceService.class, InterviewPersistenceService.class, FileStorageService.class)
            .withGenerator((registeredBean, args) -> new ResumeDeleteService(args.get(0), args.get(1), args.get(2)));
  }

  /**
   * Get the bean definition for 'resumeDeleteService'.
   */
  public static BeanDefinition getResumeDeleteServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ResumeDeleteService.class);
    beanDefinition.setInstanceSupplier(getResumeDeleteServiceInstanceSupplier());
    return beanDefinition;
  }
}
