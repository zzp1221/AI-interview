package interview.modules.resume.service;

import interview.common.ai.StructuredOutputInvoker;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.io.Resource;

/**
 * Bean definitions for {@link ResumeGradingService}.
 */
@Generated
public class ResumeGradingService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'resumeGradingService'.
   */
  private static BeanInstanceSupplier<ResumeGradingService> getResumeGradingServiceInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ResumeGradingService>forConstructor(ChatClient.Builder.class, StructuredOutputInvoker.class, Resource.class, Resource.class)
            .withGenerator((registeredBean, args) -> new ResumeGradingService(args.get(0), args.get(1), args.get(2), args.get(3)));
  }

  /**
   * Get the bean definition for 'resumeGradingService'.
   */
  public static BeanDefinition getResumeGradingServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ResumeGradingService.class);
    beanDefinition.setInstanceSupplier(getResumeGradingServiceInstanceSupplier());
    return beanDefinition;
  }
}
