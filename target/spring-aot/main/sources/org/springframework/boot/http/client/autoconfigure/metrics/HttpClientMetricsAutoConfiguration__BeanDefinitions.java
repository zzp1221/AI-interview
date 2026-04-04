package org.springframework.boot.http.client.autoconfigure.metrics;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.micrometer.metrics.MaximumAllowableTagsMeterFilter;
import org.springframework.boot.micrometer.metrics.autoconfigure.MetricsProperties;
import org.springframework.boot.micrometer.observation.autoconfigure.ObservationProperties;

/**
 * Bean definitions for {@link HttpClientMetricsAutoConfiguration}.
 */
@Generated
public class HttpClientMetricsAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'httpClientMetricsAutoConfiguration'.
   */
  public static BeanDefinition getHttpClientMetricsAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(HttpClientMetricsAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(HttpClientMetricsAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'metricsHttpClientUriTagFilter'.
   */
  private static BeanInstanceSupplier<MaximumAllowableTagsMeterFilter> getMetricsHttpClientUriTagFilterInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<MaximumAllowableTagsMeterFilter>forFactoryMethod(HttpClientMetricsAutoConfiguration.class, "metricsHttpClientUriTagFilter", ObservationProperties.class, MetricsProperties.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.boot.http.client.autoconfigure.metrics.HttpClientMetricsAutoConfiguration", HttpClientMetricsAutoConfiguration.class).metricsHttpClientUriTagFilter(args.get(0), args.get(1)));
  }

  /**
   * Get the bean definition for 'metricsHttpClientUriTagFilter'.
   */
  public static BeanDefinition getMetricsHttpClientUriTagFilterBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(MaximumAllowableTagsMeterFilter.class);
    beanDefinition.setFactoryBeanName("org.springframework.boot.http.client.autoconfigure.metrics.HttpClientMetricsAutoConfiguration");
    beanDefinition.setInstanceSupplier(getMetricsHttpClientUriTagFilterInstanceSupplier());
    return beanDefinition;
  }
}
