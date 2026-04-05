package interview.modules.resume.service;

import interview.common.security.CurrentUserProvider;
import interview.infrastructure.file.FileHashService;
import interview.infrastructure.mapper.ResumeMapper;
import interview.modules.resume.repository.ResumeAnalysisRepository;
import interview.modules.resume.repository.ResumeRepository;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import tools.jackson.databind.ObjectMapper;

/**
 * Bean definitions for {@link ResumePersistenceService}.
 */
@Generated
public class ResumePersistenceService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'resumePersistenceService'.
   */
  private static BeanInstanceSupplier<ResumePersistenceService> getResumePersistenceServiceInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<ResumePersistenceService>forConstructor(ResumeRepository.class, ResumeAnalysisRepository.class, ObjectMapper.class, ResumeMapper.class, FileHashService.class, CurrentUserProvider.class)
            .withGenerator((registeredBean, args) -> new ResumePersistenceService(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4), args.get(5)));
  }

  /**
   * Get the bean definition for 'resumePersistenceService'.
   */
  public static BeanDefinition getResumePersistenceServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ResumePersistenceService.class);
    beanDefinition.setInstanceSupplier(getResumePersistenceServiceInstanceSupplier());
    return beanDefinition;
  }
}
