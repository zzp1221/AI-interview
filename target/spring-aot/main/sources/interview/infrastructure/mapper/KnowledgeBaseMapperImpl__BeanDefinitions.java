package interview.infrastructure.mapper;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link KnowledgeBaseMapperImpl}.
 */
@Generated
public class KnowledgeBaseMapperImpl__BeanDefinitions {
  /**
   * Get the bean definition for 'knowledgeBaseMapperImpl'.
   */
  public static BeanDefinition getKnowledgeBaseMapperImplBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(KnowledgeBaseMapperImpl.class);
    beanDefinition.setInstanceSupplier(KnowledgeBaseMapperImpl::new);
    return beanDefinition;
  }
}
