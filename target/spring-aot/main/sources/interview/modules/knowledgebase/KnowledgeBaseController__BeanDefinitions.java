package interview.modules.knowledgebase;

import interview.common.security.CurrentUserProvider;
import interview.modules.knowledgebase.service.KnowledgeBaseDeleteService;
import interview.modules.knowledgebase.service.KnowledgeBaseListService;
import interview.modules.knowledgebase.service.KnowledgeBaseQueryService;
import interview.modules.knowledgebase.service.KnowledgeBaseUploadService;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link KnowledgeBaseController}.
 */
@Generated
public class KnowledgeBaseController__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'knowledgeBaseController'.
   */
  private static BeanInstanceSupplier<KnowledgeBaseController> getKnowledgeBaseControllerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<KnowledgeBaseController>forConstructor(KnowledgeBaseUploadService.class, KnowledgeBaseQueryService.class, KnowledgeBaseListService.class, KnowledgeBaseDeleteService.class, CurrentUserProvider.class)
            .withGenerator((registeredBean, args) -> new KnowledgeBaseController(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4)));
  }

  /**
   * Get the bean definition for 'knowledgeBaseController'.
   */
  public static BeanDefinition getKnowledgeBaseControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(KnowledgeBaseController.class);
    beanDefinition.setInstanceSupplier(getKnowledgeBaseControllerInstanceSupplier());
    return beanDefinition;
  }
}
