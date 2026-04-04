package org.springframework.boot.data.redis.autoconfigure.observation;

import io.micrometer.observation.ObservationRegistry;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.data.redis.autoconfigure.ClientResourcesBuilderCustomizer;

/**
 * Bean definitions for {@link LettuceObservationAutoConfiguration}.
 */
@Generated
public class LettuceObservationAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'lettuceObservationAutoConfiguration'.
   */
  public static BeanDefinition getLettuceObservationAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(LettuceObservationAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(LettuceObservationAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'lettuceObservation'.
   */
  private static BeanInstanceSupplier<ClientResourcesBuilderCustomizer> getLettuceObservationInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ClientResourcesBuilderCustomizer>forFactoryMethod(LettuceObservationAutoConfiguration.class, "lettuceObservation", ObservationRegistry.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.boot.data.redis.autoconfigure.observation.LettuceObservationAutoConfiguration", LettuceObservationAutoConfiguration.class).lettuceObservation(args.get(0)));
  }

  /**
   * Get the bean definition for 'lettuceObservation'.
   */
  public static BeanDefinition getLettuceObservationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ClientResourcesBuilderCustomizer.class);
    beanDefinition.setFactoryBeanName("org.springframework.boot.data.redis.autoconfigure.observation.LettuceObservationAutoConfiguration");
    beanDefinition.setInstanceSupplier(getLettuceObservationInstanceSupplier());
    return beanDefinition;
  }
}
