package interview.modules.resume.service;

import interview.common.config.AppConfigProperties;
import interview.infrastructure.file.FileStorageService;
import interview.infrastructure.file.FileValidationService;
import interview.modules.resume.listener.AnalyzeStreamProducer;
import interview.modules.resume.repository.ResumeRepository;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ResumeUploadService}.
 */
@Generated
public class ResumeUploadService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'resumeUploadService'.
   */
  private static BeanInstanceSupplier<ResumeUploadService> getResumeUploadServiceInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ResumeUploadService>forConstructor(ResumeParseService.class, FileStorageService.class, ResumePersistenceService.class, AppConfigProperties.class, FileValidationService.class, AnalyzeStreamProducer.class, ResumeRepository.class)
            .withGenerator((registeredBean, args) -> new ResumeUploadService(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4), args.get(5), args.get(6)));
  }

  /**
   * Get the bean definition for 'resumeUploadService'.
   */
  public static BeanDefinition getResumeUploadServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ResumeUploadService.class);
    beanDefinition.setInstanceSupplier(getResumeUploadServiceInstanceSupplier());
    return beanDefinition;
  }
}
