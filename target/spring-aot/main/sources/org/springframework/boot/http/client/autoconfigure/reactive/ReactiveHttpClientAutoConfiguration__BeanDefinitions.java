package org.springframework.boot.http.client.autoconfigure.reactive;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.http.client.reactive.ClientHttpConnectorBuilder;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.client.reactive.ClientHttpConnector;

/**
 * Bean definitions for {@link ReactiveHttpClientAutoConfiguration}.
 */
@Generated
public class ReactiveHttpClientAutoConfiguration__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'org.springframework.boot.http.client.autoconfigure.reactive.ReactiveHttpClientAutoConfiguration'.
   */
  private static BeanInstanceSupplier<ReactiveHttpClientAutoConfiguration> getReactiveHttpClientAutoConfigurationInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ReactiveHttpClientAutoConfiguration>forConstructor(Environment.class)
            .withGenerator((registeredBean, args) -> new ReactiveHttpClientAutoConfiguration(args.get(0)));
  }

  /**
   * Get the bean definition for 'reactiveHttpClientAutoConfiguration'.
   */
  public static BeanDefinition getReactiveHttpClientAutoConfigurationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ReactiveHttpClientAutoConfiguration.class);
    beanDefinition.setInstanceSupplier(getReactiveHttpClientAutoConfigurationInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'clientHttpConnectorBuilder'.
   */
  private static BeanInstanceSupplier<ClientHttpConnectorBuilder> getClientHttpConnectorBuilderInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ClientHttpConnectorBuilder>forFactoryMethod(ReactiveHttpClientAutoConfiguration.class, "clientHttpConnectorBuilder", ResourceLoader.class, ReactiveHttpClientsProperties.class, ObjectProvider.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.boot.http.client.autoconfigure.reactive.ReactiveHttpClientAutoConfiguration", ReactiveHttpClientAutoConfiguration.class).clientHttpConnectorBuilder(args.get(0), args.get(1), args.get(2)));
  }

  /**
   * Get the bean definition for 'clientHttpConnectorBuilder'.
   */
  public static BeanDefinition getClientHttpConnectorBuilderBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ClientHttpConnectorBuilder.class);
    beanDefinition.setTargetType(ResolvableType.forClass(ClientHttpConnectorBuilder.class));
    beanDefinition.setFactoryBeanName("org.springframework.boot.http.client.autoconfigure.reactive.ReactiveHttpClientAutoConfiguration");
    beanDefinition.setInstanceSupplier(getClientHttpConnectorBuilderInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'clientHttpConnector'.
   */
  private static BeanInstanceSupplier<ClientHttpConnector> getClientHttpConnectorInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ClientHttpConnector>forFactoryMethod(ReactiveHttpClientAutoConfiguration.class, "clientHttpConnector", ResourceLoader.class, ObjectProvider.class, ObjectProvider.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("org.springframework.boot.http.client.autoconfigure.reactive.ReactiveHttpClientAutoConfiguration", ReactiveHttpClientAutoConfiguration.class).clientHttpConnector(args.get(0), args.get(1), args.get(2)));
  }

  /**
   * Get the bean definition for 'clientHttpConnector'.
   */
  public static BeanDefinition getClientHttpConnectorBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ClientHttpConnector.class);
    beanDefinition.setLazyInit(true);
    beanDefinition.setFactoryBeanName("org.springframework.boot.http.client.autoconfigure.reactive.ReactiveHttpClientAutoConfiguration");
    beanDefinition.setInstanceSupplier(getClientHttpConnectorInstanceSupplier());
    return beanDefinition;
  }
}
