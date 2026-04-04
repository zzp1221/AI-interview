package interview.modules.interview;

import interview.modules.interview.service.InterviewHistoryService;
import interview.modules.interview.service.InterviewPersistenceService;
import interview.modules.interview.service.InterviewSessionService;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link InterviewController}.
 */
@Generated
public class InterviewController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'interviewController'.
   */
  private static BeanInstanceSupplier<InterviewController> getInterviewControllerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<InterviewController>forConstructor(InterviewSessionService.class, InterviewHistoryService.class, InterviewPersistenceService.class)
            .withGenerator((registeredBean, args) -> new InterviewController(args.get(0), args.get(1), args.get(2)));
  }

  /**
   * Get the bean definition for 'interviewController'.
   */
  public static BeanDefinition getInterviewControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(InterviewController.class);
    beanDefinition.setInstanceSupplier(getInterviewControllerInstanceSupplier());
    return beanDefinition;
  }
}
