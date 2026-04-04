package interview.modules.knowledgebase.repository;

import interview.modules.knowledgebase.model.KnowledgeBaseEntity;
import interview.modules.knowledgebase.model.VectorStatus;
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
 * AOT generated JPA repository implementation for {@link KnowledgeBaseRepository}.
 */
@Generated
public class KnowledgeBaseRepositoryImpl__AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public KnowledgeBaseRepositoryImpl__AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link KnowledgeBaseRepository#countByUserId(java.lang.Long)}.
   */
  public long countByUserId(Long userId) {
    String queryString = "SELECT COUNT(k) FROM KnowledgeBaseEntity k WHERE k.userId = :userId";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("userId", userId);

    return (Long) convertOne(query.getSingleResultOrNull(), false, Long.class);
  }

  /**
   * AOT generated implementation of {@link KnowledgeBaseRepository#countByUserIdAndVectorStatus(java.lang.Long,interview.modules.knowledgebase.model.VectorStatus)}.
   */
  public long countByUserIdAndVectorStatus(Long userId, VectorStatus vectorStatus) {
    String queryString = "SELECT COUNT(k) FROM KnowledgeBaseEntity k WHERE k.userId = :userId AND k.vectorStatus = :vectorStatus";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("userId", userId);
    query.setParameter("vectorStatus", vectorStatus);

    return (Long) convertOne(query.getSingleResultOrNull(), false, Long.class);
  }

  /**
   * AOT generated implementation of {@link KnowledgeBaseRepository#countByVectorStatus(interview.modules.knowledgebase.model.VectorStatus)}.
   */
  public long countByVectorStatus(VectorStatus vectorStatus) {
    String queryString = "SELECT COUNT(k) FROM KnowledgeBaseEntity k WHERE k.vectorStatus = :vectorStatus";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("vectorStatus", vectorStatus);

    return (Long) convertOne(query.getSingleResultOrNull(), false, Long.class);
  }

  /**
   * AOT generated implementation of {@link KnowledgeBaseRepository#existsByFileHash(java.lang.String)}.
   */
  public boolean existsByFileHash(String fileHash) {
    String queryString = "SELECT k.id FROM KnowledgeBaseEntity k WHERE k.fileHash = :fileHash";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("fileHash", fileHash);
    query.setMaxResults(1);

    return !query.getResultList().isEmpty();
  }

  /**
   * AOT generated implementation of {@link KnowledgeBaseRepository#existsByIdAndUserId(java.lang.Long,java.lang.Long)}.
   */
  public boolean existsByIdAndUserId(Long id, Long userId) {
    String queryString = "SELECT k.id FROM KnowledgeBaseEntity k WHERE k.id = :id AND k.userId = :userId";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("id", id);
    query.setParameter("userId", userId);
    query.setMaxResults(1);

    return !query.getResultList().isEmpty();
  }

  /**
   * AOT generated implementation of {@link KnowledgeBaseRepository#findAllByOrderByAccessCountDesc()}.
   */
  public List<KnowledgeBaseEntity> findAllByOrderByAccessCountDesc() {
    String queryString = "SELECT k FROM KnowledgeBaseEntity k ORDER BY k.accessCount desc";
    Query query = this.entityManager.createQuery(queryString);

    return (List<KnowledgeBaseEntity>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link KnowledgeBaseRepository#findAllByOrderByFileSizeDesc()}.
   */
  public List<KnowledgeBaseEntity> findAllByOrderByFileSizeDesc() {
    String queryString = "SELECT k FROM KnowledgeBaseEntity k ORDER BY k.fileSize desc";
    Query query = this.entityManager.createQuery(queryString);

    return (List<KnowledgeBaseEntity>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link KnowledgeBaseRepository#findAllByOrderByQuestionCountDesc()}.
   */
  public List<KnowledgeBaseEntity> findAllByOrderByQuestionCountDesc() {
    String queryString = "SELECT k FROM KnowledgeBaseEntity k ORDER BY k.questionCount desc";
    Query query = this.entityManager.createQuery(queryString);

    return (List<KnowledgeBaseEntity>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link KnowledgeBaseRepository#findAllByOrderByUploadedAtDesc()}.
   */
  public List<KnowledgeBaseEntity> findAllByOrderByUploadedAtDesc() {
    String queryString = "SELECT k FROM KnowledgeBaseEntity k ORDER BY k.uploadedAt desc";
    Query query = this.entityManager.createQuery(queryString);

    return (List<KnowledgeBaseEntity>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link KnowledgeBaseRepository#findAllCategories()}.
   */
  public List<String> findAllCategories() {
    String queryString = "SELECT DISTINCT k.category FROM KnowledgeBaseEntity k WHERE k.category IS NOT NULL ORDER BY k.category";
    Query query = this.entityManager.createQuery(queryString);

    return (List<String>) convertMany(query.getResultList(), false, String.class);
  }

  /**
   * AOT generated implementation of {@link KnowledgeBaseRepository#findAllCategoriesByUserId(java.lang.Long)}.
   */
  public List<String> findAllCategoriesByUserId(@Param("userId") Long userId) {
    String queryString = "SELECT DISTINCT k.category FROM KnowledgeBaseEntity k WHERE k.userId = :userId AND k.category IS NOT NULL ORDER BY k.category";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("userId", userId);

    return (List<String>) convertMany(query.getResultList(), false, String.class);
  }

  /**
   * AOT generated implementation of {@link KnowledgeBaseRepository#findByCategoryIsNullOrderByUploadedAtDesc()}.
   */
  public List<KnowledgeBaseEntity> findByCategoryIsNullOrderByUploadedAtDesc() {
    String queryString = "SELECT k FROM KnowledgeBaseEntity k WHERE k.category IS NULL ORDER BY k.uploadedAt desc";
    Query query = this.entityManager.createQuery(queryString);

    return (List<KnowledgeBaseEntity>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link KnowledgeBaseRepository#findByCategoryOrderByUploadedAtDesc(java.lang.String)}.
   */
  public List<KnowledgeBaseEntity> findByCategoryOrderByUploadedAtDesc(String category) {
    String queryString = "SELECT k FROM KnowledgeBaseEntity k WHERE k.category = :category ORDER BY k.uploadedAt desc";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("category", category);

    return (List<KnowledgeBaseEntity>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link KnowledgeBaseRepository#findByFileHash(java.lang.String)}.
   */
  public Optional<KnowledgeBaseEntity> findByFileHash(String fileHash) {
    String queryString = "SELECT k FROM KnowledgeBaseEntity k WHERE k.fileHash = :fileHash";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("fileHash", fileHash);

    return Optional.ofNullable((KnowledgeBaseEntity) convertOne(query.getSingleResultOrNull(), false, KnowledgeBaseEntity.class));
  }

  /**
   * AOT generated implementation of {@link KnowledgeBaseRepository#findByIdAndUserId(java.lang.Long,java.lang.Long)}.
   */
  public Optional<KnowledgeBaseEntity> findByIdAndUserId(Long id, Long userId) {
    String queryString = "SELECT k FROM KnowledgeBaseEntity k WHERE k.id = :id AND k.userId = :userId";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("id", id);
    query.setParameter("userId", userId);

    return Optional.ofNullable((KnowledgeBaseEntity) convertOne(query.getSingleResultOrNull(), false, KnowledgeBaseEntity.class));
  }

  /**
   * AOT generated implementation of {@link KnowledgeBaseRepository#findByIdInAndUserId(java.util.List,java.lang.Long)}.
   */
  public List<KnowledgeBaseEntity> findByIdInAndUserId(List<Long> ids, Long userId) {
    String queryString = "SELECT k FROM KnowledgeBaseEntity k WHERE k.id IN :ids AND k.userId = :userId";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("ids", ids);
    query.setParameter("userId", userId);

    return (List<KnowledgeBaseEntity>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link KnowledgeBaseRepository#findByUserIdAndCategoryIsNullOrderByUploadedAtDesc(java.lang.Long)}.
   */
  public List<KnowledgeBaseEntity> findByUserIdAndCategoryIsNullOrderByUploadedAtDesc(Long userId) {
    String queryString = "SELECT k FROM KnowledgeBaseEntity k WHERE k.userId = :userId AND k.category IS NULL ORDER BY k.uploadedAt desc";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("userId", userId);

    return (List<KnowledgeBaseEntity>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link KnowledgeBaseRepository#findByUserIdAndCategoryOrderByUploadedAtDesc(java.lang.Long,java.lang.String)}.
   */
  public List<KnowledgeBaseEntity> findByUserIdAndCategoryOrderByUploadedAtDesc(Long userId,
      String category) {
    String queryString = "SELECT k FROM KnowledgeBaseEntity k WHERE k.userId = :userId AND k.category = :category ORDER BY k.uploadedAt desc";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("userId", userId);
    query.setParameter("category", category);

    return (List<KnowledgeBaseEntity>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link KnowledgeBaseRepository#findByUserIdAndFileHash(java.lang.Long,java.lang.String)}.
   */
  public Optional<KnowledgeBaseEntity> findByUserIdAndFileHash(Long userId, String fileHash) {
    String queryString = "SELECT k FROM KnowledgeBaseEntity k WHERE k.userId = :userId AND k.fileHash = :fileHash";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("userId", userId);
    query.setParameter("fileHash", fileHash);

    return Optional.ofNullable((KnowledgeBaseEntity) convertOne(query.getSingleResultOrNull(), false, KnowledgeBaseEntity.class));
  }

  /**
   * AOT generated implementation of {@link KnowledgeBaseRepository#findByUserIdAndVectorStatusOrderByUploadedAtDesc(java.lang.Long,interview.modules.knowledgebase.model.VectorStatus)}.
   */
  public List<KnowledgeBaseEntity> findByUserIdAndVectorStatusOrderByUploadedAtDesc(Long userId,
      VectorStatus vectorStatus) {
    String queryString = "SELECT k FROM KnowledgeBaseEntity k WHERE k.userId = :userId AND k.vectorStatus = :vectorStatus ORDER BY k.uploadedAt desc";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("userId", userId);
    query.setParameter("vectorStatus", vectorStatus);

    return (List<KnowledgeBaseEntity>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link KnowledgeBaseRepository#findByUserIdOrderByUploadedAtDesc(java.lang.Long)}.
   */
  public List<KnowledgeBaseEntity> findByUserIdOrderByUploadedAtDesc(Long userId) {
    String queryString = "SELECT k FROM KnowledgeBaseEntity k WHERE k.userId = :userId ORDER BY k.uploadedAt desc";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("userId", userId);

    return (List<KnowledgeBaseEntity>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link KnowledgeBaseRepository#findByVectorStatusOrderByUploadedAtDesc(interview.modules.knowledgebase.model.VectorStatus)}.
   */
  public List<KnowledgeBaseEntity> findByVectorStatusOrderByUploadedAtDesc(
      VectorStatus vectorStatus) {
    String queryString = "SELECT k FROM KnowledgeBaseEntity k WHERE k.vectorStatus = :vectorStatus ORDER BY k.uploadedAt desc";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("vectorStatus", vectorStatus);

    return (List<KnowledgeBaseEntity>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link KnowledgeBaseRepository#incrementQuestionCountBatch(java.util.List)}.
   */
  public int incrementQuestionCountBatch(@Param("ids") List<Long> ids) {
    String queryString = "UPDATE KnowledgeBaseEntity k SET k.questionCount = k.questionCount + 1 WHERE k.id IN :ids";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("ids", ids);

    int result = query.executeUpdate();
    return result;
  }

  /**
   * AOT generated implementation of {@link KnowledgeBaseRepository#incrementQuestionCountBatchByUserId(java.lang.Long,java.util.List)}.
   */
  public int incrementQuestionCountBatchByUserId(@Param("userId") Long userId,
      @Param("ids") List<Long> ids) {
    String queryString = "UPDATE KnowledgeBaseEntity k SET k.questionCount = k.questionCount + 1 WHERE k.userId = :userId AND k.id IN :ids";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("userId", userId);
    query.setParameter("ids", ids);

    int result = query.executeUpdate();
    return result;
  }

  /**
   * AOT generated implementation of {@link KnowledgeBaseRepository#searchByKeyword(java.lang.String)}.
   */
  public List<KnowledgeBaseEntity> searchByKeyword(@Param("keyword") String keyword) {
    String queryString = "SELECT k FROM KnowledgeBaseEntity k WHERE LOWER(k.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(k.originalFilename) LIKE LOWER(CONCAT('%', :keyword, '%')) ORDER BY k.uploadedAt DESC";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("keyword", keyword);

    return (List<KnowledgeBaseEntity>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link KnowledgeBaseRepository#searchByKeywordAndUserId(java.lang.Long,java.lang.String)}.
   */
  public List<KnowledgeBaseEntity> searchByKeywordAndUserId(@Param("userId") Long userId,
      @Param("keyword") String keyword) {
    String queryString = "SELECT k FROM KnowledgeBaseEntity k WHERE k.userId = :userId AND (LOWER(k.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(k.originalFilename) LIKE LOWER(CONCAT('%', :keyword, '%'))) ORDER BY k.uploadedAt DESC";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("userId", userId);
    query.setParameter("keyword", keyword);

    return (List<KnowledgeBaseEntity>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link KnowledgeBaseRepository#sumAccessCount()}.
   */
  public long sumAccessCount() {
    String queryString = "SELECT COALESCE(SUM(k.accessCount), 0) FROM KnowledgeBaseEntity k";
    Query query = this.entityManager.createQuery(queryString);

    return (Long) convertOne(query.getSingleResultOrNull(), false, Long.class);
  }

  /**
   * AOT generated implementation of {@link KnowledgeBaseRepository#sumAccessCountByUserId(java.lang.Long)}.
   */
  public long sumAccessCountByUserId(@Param("userId") Long userId) {
    String queryString = "SELECT COALESCE(SUM(k.accessCount), 0) FROM KnowledgeBaseEntity k WHERE k.userId = :userId";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("userId", userId);

    return (Long) convertOne(query.getSingleResultOrNull(), false, Long.class);
  }

  /**
   * AOT generated implementation of {@link KnowledgeBaseRepository#sumQuestionCount()}.
   */
  public long sumQuestionCount() {
    String queryString = "SELECT COALESCE(SUM(k.questionCount), 0) FROM KnowledgeBaseEntity k";
    Query query = this.entityManager.createQuery(queryString);

    return (Long) convertOne(query.getSingleResultOrNull(), false, Long.class);
  }
}
