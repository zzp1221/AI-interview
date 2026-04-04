package interview.modules.interview.service;

import interview.infrastructure.export.PdfExportService;
import interview.infrastructure.mapper.InterviewMapper;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import tools.jackson.databind.ObjectMapper;

/**
 * Bean definitions for {@link InterviewHistoryService}.
 */
@Generated
public class InterviewHistoryService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'interviewHistoryService'.
   */
  private static BeanInstanceSupplier<InterviewHistoryService> getInterviewHistoryServiceInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<InterviewHistoryService>forConstructor(InterviewPersistenceService.class, PdfExportService.class, ObjectMapper.class, InterviewMapper.class)
            .withGenerator((registeredBean, args) -> new InterviewHistoryService(args.get(0), args.get(1), args.get(2), args.get(3)));
  }

  /**
   * Get the bean definition for 'interviewHistoryService'.
   */
  public static BeanDefinition getInterviewHistoryServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(InterviewHistoryService.class);
    beanDefinition.setInstanceSupplier(getInterviewHistoryServiceInstanceSupplier());
    return beanDefinition;
  }
}
