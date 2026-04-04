package interview.modules.interview.service;

import interview.common.ai.StructuredOutputInvoker;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.io.Resource;

/**
 * Bean definitions for {@link AnswerEvaluationService}.
 */
@Generated
public class AnswerEvaluationService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'answerEvaluationService'.
   */
  private static BeanInstanceSupplier<AnswerEvaluationService> getAnswerEvaluationServiceInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<AnswerEvaluationService>forConstructor(ChatClient.Builder.class, StructuredOutputInvoker.class, Resource.class, Resource.class, Resource.class, Resource.class, int.class)
            .withGenerator((registeredBean, args) -> new AnswerEvaluationService(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4), args.get(5), args.get(6)));
  }

  /**
   * Get the bean definition for 'answerEvaluationService'.
   */
  public static BeanDefinition getAnswerEvaluationServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(AnswerEvaluationService.class);
    beanDefinition.setInstanceSupplier(getAnswerEvaluationServiceInstanceSupplier());
    return beanDefinition;
  }
}
