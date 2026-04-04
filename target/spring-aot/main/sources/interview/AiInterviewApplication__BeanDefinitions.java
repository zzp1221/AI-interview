package interview;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link AiInterviewApplication}.
 */
@Generated
public class AiInterviewApplication__BeanDefinitions {
  /**
   * Get the bean definition for 'aiInterviewApplication'.
   */
  public static BeanDefinition getAiInterviewApplicationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(AiInterviewApplication.class);
    beanDefinition.setInstanceSupplier(AiInterviewApplication::new);
    return beanDefinition;
  }
}
