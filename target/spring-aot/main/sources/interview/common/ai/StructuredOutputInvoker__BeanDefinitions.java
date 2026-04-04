package interview.common.ai;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link StructuredOutputInvoker}.
 */
@Generated
public class StructuredOutputInvoker__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'structuredOutputInvoker'.
   */
  private static BeanInstanceSupplier<StructuredOutputInvoker> getStructuredOutputInvokerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<StructuredOutputInvoker>forConstructor(int.class, boolean.class)
            .withGenerator((registeredBean, args) -> new StructuredOutputInvoker(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'structuredOutputInvoker'.
   */
  public static BeanDefinition getStructuredOutputInvokerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(StructuredOutputInvoker.class);
    beanDefinition.setInstanceSupplier(getStructuredOutputInvokerInstanceSupplier());
    return beanDefinition;
  }
}
