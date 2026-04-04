package org.springframework.boot.restclient.autoconfigure;

import io.micrometer.observation.ObservationRegistry;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.micrometer.observation.autoconfigure.ObservationProperties;
import org.springframework.boot.restclient.RestClientCustomizer;

/**
 * Bean definitions for {@link RestClientObservationAutoConfiguration}.
 */
@Generated
public class RestClientObservationAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'restClientObservationAutoConfiguration'.
   */
  public static BeanDefinition getRestClientObservationAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(RestClientObservationAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(RestClientObservationAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'observationRestClientCustomizer'.
   */
  private static BeanInstanceSupplier<RestClientCustomizer> getObservationRestClientCustomizerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<RestClientCustomizer>forFactoryMethod(RestClientObservationAutoConfiguration.class, "observationRestClientCustomizer", ObservationRegistry.class, ObjectProvider.class, ObservationProperties.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.boot.restclient.autoconfigure.RestClientObservationAutoConfiguration", RestClientObservationAutoConfiguration.class).observationRestClientCustomizer(args.get(0), args.get(1), args.get(2)));
  }

  /**
   * Get the bean definition for 'observationRestClientCustomizer'.
   */
  public static BeanDefinition getObservationRestClientCustomizerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(RestClientCustomizer.class);
    beanDefinition.setFactoryBeanName("org.springframework.boot.restclient.autoconfigure.RestClientObservationAutoConfiguration");
    beanDefinition.setInstanceSupplier(getObservationRestClientCustomizerInstanceSupplier());
    return beanDefinition;
  }
}
