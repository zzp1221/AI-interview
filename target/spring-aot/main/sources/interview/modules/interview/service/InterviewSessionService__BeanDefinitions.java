package interview.modules.interview.service;

import interview.infrastructure.redis.InterviewSessionCache;
import interview.modules.interview.listener.EvaluateStreamProducer;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import tools.jackson.databind.ObjectMapper;

/**
 * Bean definitions for {@link InterviewSessionService}.
 */
@Generated
public class InterviewSessionService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'interviewSessionService'.
   */
  private static BeanInstanceSupplier<InterviewSessionService> getInterviewSessionServiceInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<InterviewSessionService>forConstructor(InterviewQuestionService.class, AnswerEvaluationService.class, InterviewPersistenceService.class, InterviewSessionCache.class, ObjectMapper.class, EvaluateStreamProducer.class)
            .withGenerator((registeredBean, args) -> new InterviewSessionService(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4), args.get(5)));
  }

  /**
   * Get the bean definition for 'interviewSessionService'.
   */
  public static BeanDefinition getInterviewSessionServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(InterviewSessionService.class);
    beanDefinition.setInstanceSupplier(getInterviewSessionServiceInstanceSupplier());
    return beanDefinition;
  }
}
