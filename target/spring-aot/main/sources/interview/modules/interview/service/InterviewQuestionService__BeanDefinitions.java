package interview.modules.interview.service;

import interview.common.ai.StructuredOutputInvoker;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.io.Resource;

/**
 * Bean definitions for {@link InterviewQuestionService}.
 */
@Generated
public class InterviewQuestionService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'interviewQuestionService'.
   */
  private static BeanInstanceSupplier<InterviewQuestionService> getInterviewQuestionServiceInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<InterviewQuestionService>forConstructor(ChatClient.Builder.class, StructuredOutputInvoker.class, Resource.class, Resource.class, int.class)
            .withGenerator((registeredBean, args) -> new InterviewQuestionService(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4)));
  }

  /**
   * Get the bean definition for 'interviewQuestionService'.
   */
  public static BeanDefinition getInterviewQuestionServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(InterviewQuestionService.class);
    beanDefinition.setInstanceSupplier(getInterviewQuestionServiceInstanceSupplier());
    return beanDefinition;
  }
}
