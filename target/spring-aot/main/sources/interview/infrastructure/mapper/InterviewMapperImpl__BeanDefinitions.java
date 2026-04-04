package interview.infrastructure.mapper;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link InterviewMapperImpl}.
 */
@Generated
public class InterviewMapperImpl__BeanDefinitions {
  /**
   * Get the bean definition for 'interviewMapperImpl'.
   */
  public static BeanDefinition getInterviewMapperImplBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(InterviewMapperImpl.class);
    beanDefinition.setInstanceSupplier(InterviewMapperImpl::new);
    return beanDefinition;
  }
}
