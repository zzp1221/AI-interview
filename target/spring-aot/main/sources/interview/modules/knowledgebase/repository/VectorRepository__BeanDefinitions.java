package interview.modules.knowledgebase.repository;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Bean definitions for {@link VectorRepository}.
 */
@Generated
public class VectorRepository__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'vectorRepository'.
   */
  private static BeanInstanceSupplier<VectorRepository> getVectorRepositoryInstanceSupplier() {
    return BeanInstanceSupplier.<VectorRepository>forConstructor(JdbcTemplate.class)
            .withGenerator((registeredBean, args) -> new VectorRepository(args.get(0)));
  }

  /**
   * Get the bean definition for 'vectorRepository'.
   */
  public static BeanDefinition getVectorRepositoryBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(VectorRepository.class);
    beanDefinition.setInstanceSupplier(getVectorRepositoryInstanceSupplier());
    return beanDefinition;
  }
}
