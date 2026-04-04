package org.springframework.boot.webclient.autoconfigure;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.boot.webclient.WebClientCustomizer;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Bean definitions for {@link WebClientAutoConfiguration}.
 */
@Generated
public class WebClientAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean definition for 'webClientAutoConfiguration'.
   */
  public static BeanDefinition getWebClientAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(WebClientAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(WebClientAutoConfiguration::new);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'webClientBuilder'.
   */
  private static BeanInstanceSupplier<WebClient.Builder> getWebClientBuilderInstanceSupplier() {
    return BeanInstanceSupplier.<WebClient.Builder>forFactoryMethod(WebClientAutoConfiguration.class, "webClientBuilder", ObjectProvider.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.boot.webclient.autoconfigure.WebClientAutoConfiguration", WebClientAutoConfiguration.class).webClientBuilder(args.get(0)));
  }

  /**
   * Get the bean definition for 'webClientBuilder'.
   */
  public static BeanDefinition getWebClientBuilderBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(WebClient.Builder.class);
    beanDefinition.setScope("prototype");
    beanDefinition.setFactoryBeanName("org.springframework.boot.webclient.autoconfigure.WebClientAutoConfiguration");
    beanDefinition.setInstanceSupplier(getWebClientBuilderInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'webClientHttpConnectorCustomizer'.
   */
  private static BeanInstanceSupplier<WebClientCustomizer> getWebClientHttpConnectorCustomizerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<WebClientCustomizer>forFactoryMethod(WebClientAutoConfiguration.class, "webClientHttpConnectorCustomizer", ClientHttpConnector.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.boot.webclient.autoconfigure.WebClientAutoConfiguration", WebClientAutoConfiguration.class).webClientHttpConnectorCustomizer(args.get(0)));
  }

  /**
   * Get the bean definition for 'webClientHttpConnectorCustomizer'.
   */
  public static BeanDefinition getWebClientHttpConnectorCustomizerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(WebClientCustomizer.class);
    beanDefinition.setLazyInit(true);
    beanDefinition.setFactoryBeanName("org.springframework.boot.webclient.autoconfigure.WebClientAutoConfiguration");
    beanDefinition.setInstanceSupplier(getWebClientHttpConnectorCustomizerInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'webClientSsl'.
   */
  private static BeanInstanceSupplier<AutoConfiguredWebClientSsl> getWebClientSslInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<AutoConfiguredWebClientSsl>forFactoryMethod(WebClientAutoConfiguration.class, "webClientSsl", ResourceLoader.class, ObjectProvider.class, ObjectProvider.class, SslBundles.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.boot.webclient.autoconfigure.WebClientAutoConfiguration", WebClientAutoConfiguration.class).webClientSsl(args.get(0), args.get(1), args.get(2), args.get(3)));
  }

  /**
   * Get the bean definition for 'webClientSsl'.
   */
  public static BeanDefinition getWebClientSslBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(AutoConfiguredWebClientSsl.class);
    beanDefinition.setFactoryBeanName("org.springframework.boot.webclient.autoconfigure.WebClientAutoConfiguration");
    beanDefinition.setInstanceSupplier(getWebClientSslInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Bean definitions for {@link WebClientAutoConfiguration.WebClientCodecsConfiguration}.
   */
  @Generated
  public static class WebClientCodecsConfiguration {
    /**
     * Get the bean definition for 'webClientCodecsConfiguration'.
     */
    public static BeanDefinition getWebClientCodecsConfigurationBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(WebClientAutoConfiguration.WebClientCodecsConfiguration.class);
      beanDefinition.setInstanceSupplier(WebClientAutoConfiguration.WebClientCodecsConfiguration::new);
      return beanDefinition;
    }

    /**
     * Get the bean instance supplier for 'exchangeStrategiesCustomizer'.
     */
    private static BeanInstanceSupplier<WebClientCodecCustomizer> getExchangeStrategiesCustomizerInstanceSupplier(
        ) {
      return BeanInstanceSupplier.<WebClientCodecCustomizer>forFactoryMethod(WebClientAutoConfiguration.WebClientCodecsConfiguration.class, "exchangeStrategiesCustomizer", ObjectProvider.class)
              .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.boot.webclient.autoconfigure.WebClientAutoConfiguration$WebClientCodecsConfiguration", WebClientAutoConfiguration.WebClientCodecsConfiguration.class).exchangeStrategiesCustomizer(args.get(0)));
    }

    /**
     * Get the bean definition for 'exchangeStrategiesCustomizer'.
     */
    public static BeanDefinition getExchangeStrategiesCustomizerBeanDefinition() {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(WebClientCodecCustomizer.class);
      beanDefinition.setFactoryBeanName("org.springframework.boot.webclient.autoconfigure.WebClientAutoConfiguration$WebClientCodecsConfiguration");
      beanDefinition.setInstanceSupplier(getExchangeStrategiesCustomizerInstanceSupplier());
      return beanDefinition;
    }
  }
}
