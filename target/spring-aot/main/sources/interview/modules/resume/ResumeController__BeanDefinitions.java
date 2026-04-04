package interview.modules.resume;

import interview.modules.resume.listener.AnalyzeStreamProducer;
import interview.modules.resume.service.ResumeDeleteService;
import interview.modules.resume.service.ResumeHistoryService;
import interview.modules.resume.service.ResumePersistenceService;
import interview.modules.resume.service.ResumeUploadService;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ResumeController}.
 */
@Generated
public class ResumeController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'resumeController'.
   */
  private static BeanInstanceSupplier<ResumeController> getResumeControllerInstanceSupplier() {
    return BeanInstanceSupplier.<ResumeController>forConstructor(ResumeUploadService.class, ResumeHistoryService.class, ResumeDeleteService.class, ResumePersistenceService.class, AnalyzeStreamProducer.class)
            .withGenerator((registeredBean, args) -> new ResumeController(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4)));
  }

  /**
   * Get the bean definition for 'resumeController'.
   */
  public static BeanDefinition getResumeControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ResumeController.class);
    beanDefinition.setInstanceSupplier(getResumeControllerInstanceSupplier());
    return beanDefinition;
  }
}
