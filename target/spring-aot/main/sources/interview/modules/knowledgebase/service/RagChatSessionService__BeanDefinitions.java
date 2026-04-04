package interview.modules.knowledgebase.service;

import interview.infrastructure.mapper.KnowledgeBaseMapper;
import interview.infrastructure.mapper.RagChatMapper;
import interview.modules.knowledgebase.repository.KnowledgeBaseRepository;
import interview.modules.knowledgebase.repository.RagChatMessageRepository;
import interview.modules.knowledgebase.repository.RagChatSessionRepository;
import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link RagChatSessionService}.
 */
@Generated
public class RagChatSessionService__BeanDefinitions {
  /**
   * Get the bean instance supplier for 'ragChatSessionService'.
   */
  private static BeanInstanceSupplier<RagChatSessionService> getRagChatSessionServiceInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<RagChatSessionService>forConstructor(RagChatSessionRepository.class, RagChatMessageRepository.class, KnowledgeBaseRepository.class, KnowledgeBaseQueryService.class, RagChatMapper.class, KnowledgeBaseMapper.class)
            .withGenerator((registeredBean, args) -> new RagChatSessionService(args.get(0), args.get(1), args.get(2), args.get(3), args.get(4), args.get(5)));
  }

  /**
   * Get the bean definition for 'ragChatSessionService'.
   */
  public static BeanDefinition getRagChatSessionServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(RagChatSessionService.class);
    beanDefinition.setInstanceSupplier(getRagChatSessionServiceInstanceSupplier());
    return beanDefinition;
  }
}
