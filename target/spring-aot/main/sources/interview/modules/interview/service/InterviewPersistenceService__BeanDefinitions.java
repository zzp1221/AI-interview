package interview.modules.interview.service;

import interview.modules.interview.repository.InterviewAnswerRepository;
import interview.modules.interview.repository.InterviewSessionRepository;
import interview.modules.resume.repository.ResumeRepository;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import tools.jackson.databind.ObjectMapper;

/**
 * Bean definitions for {@link InterviewPersistenceService}.
 */
@Generated
public class InterviewPersistenceService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'interviewPersistenceService'.
   */
  private static BeanInstanceSupplier<InterviewPersistenceService> getInterviewPersistenceServiceInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<InterviewPersistenceService>forConstructor(InterviewSessionRepository.class, InterviewAnswerRepository.class, ResumeRepository.class, ObjectMapper.class)
            .withGenerator((registeredBean, args) -> new InterviewPersistenceService(args.get(0), args.get(1), args.get(2), args.get(3)));
  }

  /**
   * Get the bean definition for 'interviewPersistenceService'.
   */
  public static BeanDefinition getInterviewPersistenceServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(InterviewPersistenceService.class);
    beanDefinition.setInstanceSupplier(getInterviewPersistenceServiceInstanceSupplier());
    return beanDefinition;
  }
}
