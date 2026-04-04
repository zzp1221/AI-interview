package org.springframework.boot.webclient.autoconfigure;

import io.micrometer.observation.ObservationRegistry;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.micrometer.observation.autoconfigure.ObservationProperties;
import org.springframework.boot.webclient.observation.ObservationWebClientCustomizer;

/**
 * Bean definitions for {@link WebClientObservationAutoConfiguration}.
 */
@Generated
public class WebClientObservationAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'webClientObservationAutoConfiguration'.
   */
  public static BeanDefinition getWebClientObservationAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(WebClientObservationAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(WebClientObservationAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'observationWebClientCustomizer'.
   */
  private static BeanInstanceSupplier<ObservationWebClientCustomizer> getObservationWebClientCustomizerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ObservationWebClientCustomizer>forFactoryMethod(WebClientObservationAutoConfiguration.class, "observationWebClientCustomizer", ObservationRegistry.class, ObjectProvider.class, ObservationProperties.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.boot.webclient.autoconfigure.WebClientObservationAutoConfiguration", WebClientObservationAutoConfiguration.class).observationWebClientCustomizer(args.get(0), args.get(1), args.get(2)));
  }

  /**
   * Get the bean definition for 'observationWebClientCustomizer'.
   */
  public static BeanDefinition getObservationWebClientCustomizerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ObservationWebClientCustomizer.class);
    beanDefinition.setFactoryBeanName("org.springframework.boot.webclient.autoconfigure.WebClientObservationAutoConfiguration");
    beanDefinition.setInstanceSupplier(getObservationWebClientCustomizerInstanceSupplier());
    return beanDefinition;
  }
}
