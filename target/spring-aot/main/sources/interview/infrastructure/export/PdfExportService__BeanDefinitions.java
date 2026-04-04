package interview.infrastructure.export;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import tools.jackson.databind.ObjectMapper;

/**
 * Bean definitions for {@link PdfExportService}.
 */
@Generated
public class PdfExportService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'pdfExportService'.
   */
  private static BeanInstanceSupplier<PdfExportService> getPdfExportServiceInstanceSupplier() {
    return BeanInstanceSupplier.<PdfExportService>forConstructor(ObjectMapper.class)
            .withGenerator((registeredBean, args) -> new PdfExportService(args.get(0)));
  }

  /**
   * Get the bean definition for 'pdfExportService'.
   */
  public static BeanDefinition getPdfExportServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(PdfExportService.class);
    beanDefinition.setInstanceSupplier(getPdfExportServiceInstanceSupplier());
    return beanDefinition;
  }
}
