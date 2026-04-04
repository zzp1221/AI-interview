package org.springframework.boot.restclient.autoconfigure;

import io.micrometer.observation.ObservationRegistry;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.micrometer.observation.autoconfigure.ObservationProperties;
import org.springframework.boot.restclient.observation.ObservationRestTemplateCustomizer;

/**
 * Bean definitions for {@link RestTemplateObservationAutoConfiguration}.
 */
@Generated
public class RestTemplateObservationAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'restTemplateObservationAutoConfiguration'.
   */
  public static BeanDefinition getRestTemplateObservationAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(RestTemplateObservationAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(RestTemplateObservationAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'observationRestTemplateCustomizer'.
   */
  private static BeanInstanceSupplier<ObservationRestTemplateCustomizer> getObservationRestTemplateCustomizerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ObservationRestTemplateCustomizer>forFactoryMethod(RestTemplateObservationAutoConfiguration.class, "observationRestTemplateCustomizer", ObservationRegistry.class, ObjectProvider.class, ObservationProperties.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.boot.restclient.autoconfigure.RestTemplateObservationAutoConfiguration", RestTemplateObservationAutoConfiguration.class).observationRestTemplateCustomizer(args.get(0), args.get(1), args.get(2)));
  }

  /**
   * Get the bean definition for 'observationRestTemplateCustomizer'.
   */
  public static BeanDefinition getObservationRestTemplateCustomizerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ObservationRestTemplateCustomizer.class);
    beanDefinition.setFactoryBeanName("org.springframework.boot.restclient.autoconfigure.RestTemplateObservationAutoConfiguration");
    beanDefinition.setInstanceSupplier(getObservationRestTemplateCustomizerInstanceSupplier());
    return beanDefinition;
  }
}
