package interview.modules.interview.repository;

import interview.modules.interview.model.InterviewSessionEntity;
import interview.modules.resume.model.ResumeEntity;
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
 * AOT generated JPA repository implementation for {@link InterviewSessionRepository}.
 */
@Generated
public class InterviewSessionRepositoryImpl__AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public InterviewSessionRepositoryImpl__AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link InterviewSessionRepository#findByResumeIdAndStatusIn(java.lang.Long,java.util.List)}.
   */
  public Optional<InterviewSessionEntity> findByResumeIdAndStatusIn(Long resumeId,
      List<InterviewSessionEntity.SessionStatus> statuses) {
    String queryString = "SELECT i FROM InterviewSessionEntity i WHERE i.resume.id = :resumeId AND i.status IN :statuses";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("resumeId", resumeId);
    query.setParameter("statuses", statuses);

    return Optional.ofNullable((InterviewSessionEntity) convertOne(query.getSingleResultOrNull(), false, InterviewSessionEntity.class));
  }

  /**
   * AOT generated implementation of {@link InterviewSessionRepository#findByResumeIdOrderByCreatedAtDesc(java.lang.Long)}.
   */
  public List<InterviewSessionEntity> findByResumeIdOrderByCreatedAtDesc(Long resumeId) {
    String queryString = "SELECT i FROM InterviewSessionEntity i WHERE i.resume.id = :resumeId ORDER BY i.createdAt desc";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("resumeId", resumeId);

    return (List<InterviewSessionEntity>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link InterviewSessionRepository#findByResumeOrderByCreatedAtDesc(interview.modules.resume.model.ResumeEntity)}.
   */
  public List<InterviewSessionEntity> findByResumeOrderByCreatedAtDesc(ResumeEntity resume) {
    String queryString = "SELECT i FROM InterviewSessionEntity i WHERE i.resume = :resume ORDER BY i.createdAt desc";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("resume", resume);

    return (List<InterviewSessionEntity>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link InterviewSessionRepository#findBySessionId(java.lang.String)}.
   */
  public Optional<InterviewSessionEntity> findBySessionId(String sessionId) {
    String queryString = "SELECT i FROM InterviewSessionEntity i WHERE i.sessionId = :sessionId";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("sessionId", sessionId);

    return Optional.ofNullable((InterviewSessionEntity) convertOne(query.getSingleResultOrNull(), false, InterviewSessionEntity.class));
  }

  /**
   * AOT generated implementation of {@link InterviewSessionRepository#findBySessionIdWithResume(java.lang.String)}.
   */
  public Optional<InterviewSessionEntity> findBySessionIdWithResume(
      @Param("sessionId") String sessionId) {
    String queryString = "SELECT s FROM InterviewSessionEntity s JOIN FETCH s.resume WHERE s.sessionId = :sessionId";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("sessionId", sessionId);

    return Optional.ofNullable((InterviewSessionEntity) convertOne(query.getSingleResultOrNull(), false, InterviewSessionEntity.class));
  }

  /**
   * AOT generated implementation of {@link InterviewSessionRepository#findFirstByResumeIdAndStatusInOrderByCreatedAtDesc(java.lang.Long,java.util.List)}.
   */
  public Optional<InterviewSessionEntity> findFirstByResumeIdAndStatusInOrderByCreatedAtDesc(
      Long resumeId, List<InterviewSessionEntity.SessionStatus> statuses) {
    String queryString = "SELECT i FROM InterviewSessionEntity i WHERE i.resume.id = :resumeId AND i.status IN :statuses ORDER BY i.createdAt desc";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("resumeId", resumeId);
    query.setParameter("statuses", statuses);
    if (query.getMaxResults() != Integer.MAX_VALUE) {
      if (query.getMaxResults() > 1 && query.getFirstResult() > 0) {
        query.setFirstResult(query.getFirstResult() - (query.getMaxResults() - 1));
      }
    }
    query.setMaxResults(1);

    return Optional.ofNullable((InterviewSessionEntity) convertOne(query.getSingleResultOrNull(), false, InterviewSessionEntity.class));
  }

  /**
   * AOT generated implementation of {@link InterviewSessionRepository#findTop10ByResumeIdOrderByCreatedAtDesc(java.lang.Long)}.
   */
  public List<InterviewSessionEntity> findTop10ByResumeIdOrderByCreatedAtDesc(Long resumeId) {
    String queryString = "SELECT i FROM InterviewSessionEntity i WHERE i.resume.id = :resumeId ORDER BY i.createdAt desc";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("resumeId", resumeId);
    if (query.getMaxResults() != Integer.MAX_VALUE) {
      if (query.getMaxResults() > 10 && query.getFirstResult() > 0) {
        query.setFirstResult(query.getFirstResult() - (query.getMaxResults() - 10));
      }
    }
    query.setMaxResults(10);

    return (List<InterviewSessionEntity>) query.getResultList();
  }
}
