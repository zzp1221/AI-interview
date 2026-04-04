package interview.modules.resume.service;

import interview.infrastructure.export.PdfExportService;
import interview.infrastructure.mapper.InterviewMapper;
import interview.infrastructure.mapper.ResumeMapper;
import interview.modules.interview.service.InterviewPersistenceService;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import tools.jackson.databind.ObjectMapper;

/**
 * Bean definitions for {@link ResumeHistoryService}.
 */
@Generated
public class ResumeHistoryService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'resumeHistoryService'.
   */
  private static BeanInstanceSupplier<ResumeHistoryService> getResumeHistoryServiceInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ResumeHistoryService>forConstructor(ResumePersistenceService.class, InterviewPersistenceService.class, PdfExportService.class, ObjectMapper.class, ResumeMapper.class, InterviewMapper.class)
            .withGenerator((registeredBean, args) -> new ResumeHistoryService(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4), args.get(5)));
  }

  /**
   * Get the bean definition for 'resumeHistoryService'.
   */
  public static BeanDefinition getResumeHistoryServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ResumeHistoryService.class);
    beanDefinition.setInstanceSupplier(getResumeHistoryServiceInstanceSupplier());
    return beanDefinition;
  }
}
