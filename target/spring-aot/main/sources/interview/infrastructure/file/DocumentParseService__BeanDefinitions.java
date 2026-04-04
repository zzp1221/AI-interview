package interview.infrastructure.file;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link DocumentParseService}.
 */
@Generated
public class DocumentParseService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'documentParseService'.
   */
  private static BeanInstanceSupplier<DocumentParseService> getDocumentParseServiceInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<DocumentParseService>forConstructor(TextCleaningService.class)
            .withGenerator((registeredBean, args) -> new DocumentParseService(args.get(0)));
  }

  /**
   * Get the bean definition for 'documentParseService'.
   */
  public static BeanDefinition getDocumentParseServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(DocumentParseService.class);
    beanDefinition.setInstanceSupplier(getDocumentParseServiceInstanceSupplier());
    return beanDefinition;
  }
}
