package interview.modules.resume.repository;

import interview.modules.resume.model.ResumeEntity;
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

/**
 * AOT generated JPA repository implementation for {@link ResumeRepository}.
 */
@Generated
public class ResumeRepositoryImpl__AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public ResumeRepositoryImpl__AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link ResumeRepository#existsByFileHash(java.lang.String)}.
   */
  public boolean existsByFileHash(String fileHash) {
    String queryString = "SELECT r.id FROM ResumeEntity r WHERE r.fileHash = :fileHash";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("fileHash", fileHash);
    query.setMaxResults(1);

    return !query.getResultList().isEmpty();
  }

  /**
   * AOT generated implementation of {@link ResumeRepository#findByFileHash(java.lang.String)}.
   */
  public Optional<ResumeEntity> findByFileHash(String fileHash) {
    String queryString = "SELECT r FROM ResumeEntity r WHERE r.fileHash = :fileHash";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("fileHash", fileHash);

    return Optional.ofNullable((ResumeEntity) convertOne(query.getSingleResultOrNull(), false, ResumeEntity.class));
  }

  /**
   * AOT generated implementation of {@link ResumeRepository#findByIdAndUserId(java.lang.Long,java.lang.Long)}.
   */
  public Optional<ResumeEntity> findByIdAndUserId(Long id, Long userId) {
    String queryString = "SELECT r FROM ResumeEntity r WHERE r.id = :id AND r.userId = :userId";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("id", id);
    query.setParameter("userId", userId);

    return Optional.ofNullable((ResumeEntity) convertOne(query.getSingleResultOrNull(), false, ResumeEntity.class));
  }

  /**
   * AOT generated implementation of {@link ResumeRepository#findByUserIdAndFileHash(java.lang.Long,java.lang.String)}.
   */
  public Optional<ResumeEntity> findByUserIdAndFileHash(Long userId, String fileHash) {
    String queryString = "SELECT r FROM ResumeEntity r WHERE r.userId = :userId AND r.fileHash = :fileHash";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("userId", userId);
    query.setParameter("fileHash", fileHash);

    return Optional.ofNullable((ResumeEntity) convertOne(query.getSingleResultOrNull(), false, ResumeEntity.class));
  }

  /**
   * AOT generated implementation of {@link ResumeRepository#findByUserIdOrderByUploadedAtDesc(java.lang.Long)}.
   */
  public List<ResumeEntity> findByUserIdOrderByUploadedAtDesc(Long userId) {
    String queryString = "SELECT r FROM ResumeEntity r WHERE r.userId = :userId ORDER BY r.uploadedAt desc";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("userId", userId);

    return (List<ResumeEntity>) query.getResultList();
  }
}
