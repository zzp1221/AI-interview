package interview.modules.knowledgebase.repository;

import interview.modules.knowledgebase.model.RagChatSessionEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
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
 * AOT generated JPA repository implementation for {@link RagChatSessionRepository}.
 */
@Generated
public class RagChatSessionRepositoryImpl__AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public RagChatSessionRepositoryImpl__AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link RagChatSessionRepository#existsByIdAndUserId(java.lang.Long,java.lang.Long)}.
   */
  public boolean existsByIdAndUserId(Long id, Long userId) {
    String queryString = "SELECT r.id FROM RagChatSessionEntity r WHERE r.id = :id AND r.userId = :userId";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("id", id);
    query.setParameter("userId", userId);
    query.setMaxResults(1);

    return !query.getResultList().isEmpty();
  }

  /**
   * AOT generated implementation of {@link RagChatSessionRepository#findAllByOrderByUpdatedAtDesc()}.
   */
  public List<RagChatSessionEntity> findAllByOrderByUpdatedAtDesc() {
    String queryString = "SELECT r FROM RagChatSessionEntity r ORDER BY r.updatedAt desc";
    Query query = this.entityManager.createQuery(queryString);

    return (List<RagChatSessionEntity>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link RagChatSessionRepository#findAllOrderByPinnedAndUpdatedAtDesc()}.
   */
  public List<RagChatSessionEntity> findAllOrderByPinnedAndUpdatedAtDesc() {
    String queryString = "SELECT s FROM RagChatSessionEntity s ORDER BY s.isPinned DESC, s.updatedAt DESC";
    Query query = this.entityManager.createQuery(queryString);

    return (List<RagChatSessionEntity>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link RagChatSessionRepository#findAllOrderByPinnedAndUpdatedAtDesc(java.lang.Long)}.
   */
  public List<RagChatSessionEntity> findAllOrderByPinnedAndUpdatedAtDesc(
      @Param("userId") Long userId) {
    String queryString = "SELECT s FROM RagChatSessionEntity s WHERE s.userId = :userId ORDER BY s.isPinned DESC, s.updatedAt DESC";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("userId", userId);

    return (List<RagChatSessionEntity>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link RagChatSessionRepository#findByIdAndUserIdWithKnowledgeBases(java.lang.Long,java.lang.Long)}.
   */
  public Optional<RagChatSessionEntity> findByIdAndUserIdWithKnowledgeBases(@Param("id") Long id,
      @Param("userId") Long userId) {
    String queryString = "SELECT s FROM RagChatSessionEntity s LEFT JOIN FETCH s.knowledgeBases WHERE s.id = :id AND s.userId = :userId";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("id", id);
    query.setParameter("userId", userId);

    return Optional.ofNullable((RagChatSessionEntity) convertOne(query.getSingleResultOrNull(), false, RagChatSessionEntity.class));
  }

  /**
   * AOT generated implementation of {@link RagChatSessionRepository#findByIdAndUserIdWithMessagesAndKnowledgeBases(java.lang.Long,java.lang.Long)}.
   */
  public Optional<RagChatSessionEntity> findByIdAndUserIdWithMessagesAndKnowledgeBases(
      @Param("id") Long id, @Param("userId") Long userId) {
    String queryString = "SELECT DISTINCT s FROM RagChatSessionEntity s LEFT JOIN FETCH s.knowledgeBases WHERE s.id = :id AND s.userId = :userId";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("id", id);
    query.setParameter("userId", userId);

    return Optional.ofNullable((RagChatSessionEntity) convertOne(query.getSingleResultOrNull(), false, RagChatSessionEntity.class));
  }

  /**
   * AOT generated implementation of {@link RagChatSessionRepository#findByIdWithKnowledgeBases(java.lang.Long)}.
   */
  public Optional<RagChatSessionEntity> findByIdWithKnowledgeBases(@Param("id") Long id) {
    String queryString = "SELECT s FROM RagChatSessionEntity s LEFT JOIN FETCH s.knowledgeBases WHERE s.id = :id";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("id", id);

    return Optional.ofNullable((RagChatSessionEntity) convertOne(query.getSingleResultOrNull(), false, RagChatSessionEntity.class));
  }

  /**
   * AOT generated implementation of {@link RagChatSessionRepository#findByIdWithMessagesAndKnowledgeBases(java.lang.Long)}.
   */
  public Optional<RagChatSessionEntity> findByIdWithMessagesAndKnowledgeBases(
      @Param("id") Long id) {
    String queryString = "SELECT DISTINCT s FROM RagChatSessionEntity s LEFT JOIN FETCH s.knowledgeBases WHERE s.id = :id";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("id", id);

    return Optional.ofNullable((RagChatSessionEntity) convertOne(query.getSingleResultOrNull(), false, RagChatSessionEntity.class));
  }

  /**
   * AOT generated implementation of {@link RagChatSessionRepository#findByKnowledgeBaseIds(java.util.List)}.
   */
  public List<RagChatSessionEntity> findByKnowledgeBaseIds(
      @Param("kbIds") List<Long> knowledgeBaseIds) {
    String queryString = "SELECT DISTINCT s FROM RagChatSessionEntity s JOIN s.knowledgeBases kb WHERE kb.id IN :kbIds ORDER BY s.updatedAt DESC";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("kbIds", knowledgeBaseIds);

    return (List<RagChatSessionEntity>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link RagChatSessionRepository#findByStatusOrderByUpdatedAtDesc(interview.modules.knowledgebase.model.RagChatSessionEntity$SessionStatus)}.
   */
  public List<RagChatSessionEntity> findByStatusOrderByUpdatedAtDesc(
      RagChatSessionEntity.SessionStatus status) {
    String queryString = "SELECT r FROM RagChatSessionEntity r WHERE r.status = :status ORDER BY r.updatedAt desc";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("status", status);

    return (List<RagChatSessionEntity>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link RagChatSessionRepository#findByUserIdAndKnowledgeBaseIds(java.lang.Long,java.util.List)}.
   */
  public List<RagChatSessionEntity> findByUserIdAndKnowledgeBaseIds(@Param("userId") Long userId,
      @Param("kbIds") List<Long> knowledgeBaseIds) {
    String queryString = "SELECT DISTINCT s FROM RagChatSessionEntity s JOIN s.knowledgeBases kb WHERE s.userId = :userId AND kb.id IN :kbIds ORDER BY s.updatedAt DESC";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("userId", userId);
    query.setParameter("kbIds", knowledgeBaseIds);

    return (List<RagChatSessionEntity>) query.getResultList();
  }
}
