package interview.modules.knowledgebase.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.io.Resource;

/**
 * Bean definitions for {@link KnowledgeBaseQueryService}.
 */
@Generated
public class KnowledgeBaseQueryService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'knowledgeBaseQueryService'.
   */
  private static BeanInstanceSupplier<KnowledgeBaseQueryService> getKnowledgeBaseQueryServiceInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<KnowledgeBaseQueryService>forConstructor(ChatClient.Builder.class, KnowledgeBaseVectorService.class, KnowledgeBaseListService.class, KnowledgeBaseCountService.class, Resource.class, Resource.class, Resource.class, boolean.class, int.class, int.class, int.class, int.class, double.class, double.class)
            .withGenerator((registeredBean, args) -> new KnowledgeBaseQueryService(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4), args.get(5), args.get(6), args.get(7), args.get(8), args.get(9), args.get(10), args.get(11), args.get(12), args.get(13)));
  }

  /**
   * Get the bean definition for 'knowledgeBaseQueryService'.
   */
  public static BeanDefinition getKnowledgeBaseQueryServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(KnowledgeBaseQueryService.class);
    beanDefinition.setInstanceSupplier(getKnowledgeBaseQueryServiceInstanceSupplier());
    return beanDefinition;
  }
}
