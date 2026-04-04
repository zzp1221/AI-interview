package interview.common.config;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ConfigurationClassUtils;
import org.springframework.web.filter.CorsFilter;

/**
 * Bean definitions for {@link CorsConfig}.
 */
@Generated
public class CorsConfig__BeanDefinitions {
  /**
   * Get the bean definition for 'corsConfig'.
   */
  public static BeanDefinition getCorsConfigBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(CorsConfig.class);
    beanDefinition.setTargetType(CorsConfig.class);
    ConfigurationClassUtils.initializeConfigurationClass(CorsConfig.class);
    InstanceSupplier<CorsConfig> instanceSupplier = InstanceSupplier.using(CorsConfig$$SpringCGLIB$$0::new);
    instanceSupplier = instanceSupplier.andThen(CorsConfig__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'corsFilter'.
   */
  private static BeanInstanceSupplier<CorsFilter> getCorsFilterInstanceSupplier() {
    return BeanInstanceSupplier.<CorsFilter>forFactoryMethod(CorsConfig$$SpringCGLIB$$0.class, "corsFilter")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean("corsConfig", CorsConfig.class).corsFilter());
  }

  /**
   * Get the bean definition for 'corsFilter'.
   */
  public static BeanDefinition getCorsFilterBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(CorsFilter.class);
    beanDefinition.setFactoryBeanName("corsConfig");
    beanDefinition.setInstanceSupplier(getCorsFilterInstanceSupplier());
    return beanDefinition;
  }
}
