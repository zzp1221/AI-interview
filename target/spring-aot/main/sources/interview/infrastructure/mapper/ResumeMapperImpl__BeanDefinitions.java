package interview.infrastructure.mapper;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ResumeMapperImpl}.
 */
@Generated
public class ResumeMapperImpl__BeanDefinitions {
  /**
   * Get the bean definition for 'resumeMapperImpl'.
   */
  public static BeanDefinition getResumeMapperImplBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ResumeMapperImpl.class);
    beanDefinition.setInstanceSupplier(ResumeMapperImpl::new);
    return beanDefinition;
  }
}
