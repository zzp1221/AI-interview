package interview.modules.knowledgebase.repository;

import interview.modules.knowledgebase.model.RagChatMessageEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.springframework.aot.generate.Generated;
import org.springframework.data.jpa.repository.aot.AotRepositoryFragmentSupport;
import org.springframework.data.jpa.repository.query.QueryEnhancerSelector;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;
import org.springframework.data.repository.query.Param;

/**
 * AOT generated JPA repository implementation for {@link RagChatMessageRepository}.
 */
@Generated
public class RagChatMessageRepositoryImpl__AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public RagChatMessageRepositoryImpl__AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link RagChatMessageRepository#countBySessionId(java.lang.Long)}.
   */
  public Integer countBySessionId(@Param("sessionId") Long sessionId) {
    String queryString = "SELECT COUNT(m) FROM RagChatMessageEntity m WHERE m.session.id = :sessionId";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("sessionId", sessionId);

    return (Integer) convertOne(query.getSingleResultOrNull(), false, Integer.class);
  }

  /**
   * AOT generated implementation of {@link RagChatMessageRepository#countByType(interview.modules.knowledgebase.model.RagChatMessageEntity$MessageType)}.
   */
  public long countByType(RagChatMessageEntity.MessageType type) {
    String queryString = "SELECT COUNT(r) FROM RagChatMessageEntity r WHERE r.type = :type";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("type", type);

    return (Long) convertOne(query.getSingleResultOrNull(), false, Long.class);
  }

  /**
   * AOT generated implementation of {@link RagChatMessageRepository#countByUserIdAndType(java.lang.Long,interview.modules.knowledgebase.model.RagChatMessageEntity$MessageType)}.
   */
  public long countByUserIdAndType(@Param("userId") Long userId,
      @Param("type") RagChatMessageEntity.MessageType type) {
    String queryString = "SELECT COUNT(m) FROM RagChatMessageEntity m WHERE m.session.userId = :userId AND m.type = :type";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("userId", userId);
    query.setParameter("type", type);

    return (Long) convertOne(query.getSingleResultOrNull(), false, Long.class);
  }

  /**
   * AOT generated implementation of {@link RagChatMessageRepository#deleteBySessionId(java.lang.Long)}.
   */
  public void deleteBySessionId(Long sessionId) {
    String queryString = "SELECT r FROM RagChatMessageEntity r WHERE r.session.id = :sessionId";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("sessionId", sessionId);

    List resultList = query.getResultList();
    resultList.forEach(entityManager::remove);
    return;
  }

  /**
   * AOT generated implementation of {@link RagChatMessageRepository#findBySessionIdAndCompletedFalse(java.lang.Long)}.
   */
  public List<RagChatMessageEntity> findBySessionIdAndCompletedFalse(Long sessionId) {
    String queryString = "SELECT r FROM RagChatMessageEntity r WHERE r.session.id = :sessionId AND r.completed = FALSE";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("sessionId", sessionId);

    return (List<RagChatMessageEntity>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link RagChatMessageRepository#findBySessionIdOrderByMessageOrderAsc(java.lang.Long)}.
   */
  public List<RagChatMessageEntity> findBySessionIdOrderByMessageOrderAsc(Long sessionId) {
    String queryString = "SELECT r FROM RagChatMessageEntity r WHERE r.session.id = :sessionId ORDER BY r.messageOrder asc";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("sessionId", sessionId);

    return (List<RagChatMessageEntity>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link RagChatMessageRepository#findTopBySessionIdOrderByMessageOrderDesc(java.lang.Long)}.
   */
  public Optional<RagChatMessageEntity> findTopBySessionIdOrderByMessageOrderDesc(Long sessionId) {
    String queryString = "SELECT r FROM RagChatMessageEntity r WHERE r.session.id = :sessionId ORDER BY r.messageOrder desc";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("sessionId", sessionId);
    if (query.getMaxResults() != Integer.MAX_VALUE) {
      if (query.getMaxResults() > 1 && query.getFirstResult() > 0) {
        query.setFirstResult(query.getFirstResult() - (query.getMaxResults() - 1));
      }
    }
    query.setMaxResults(1);

    return Optional.ofNullable((RagChatMessageEntity) convertOne(query.getSingleResultOrNull(), false, RagChatMessageEntity.class));
  }
}
