package interview.modules.resume.repository;

import interview.modules.resume.model.ResumeAnalysisEntity;
import interview.modules.resume.model.ResumeEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import org.springframework.aot.generate.Generated;
import org.springframework.data.jpa.repository.aot.AotRepositoryFragmentSupport;
import org.springframework.data.jpa.repository.query.QueryEnhancerSelector;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;

/**
 * AOT generated JPA repository implementation for {@link ResumeAnalysisRepository}.
 */
@Generated
public class ResumeAnalysisRepositoryImpl__AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public ResumeAnalysisRepositoryImpl__AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link ResumeAnalysisRepository#findByResumeIdOrderByAnalyzedAtDesc(java.lang.Long)}.
   */
  public List<ResumeAnalysisEntity> findByResumeIdOrderByAnalyzedAtDesc(Long resumeId) {
    String queryString = "SELECT r FROM ResumeAnalysisEntity r WHERE r.resume.id = :resumeId ORDER BY r.analyzedAt desc";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("resumeId", resumeId);

    return (List<ResumeAnalysisEntity>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link ResumeAnalysisRepository#findByResumeOrderByAnalyzedAtDesc(interview.modules.resume.model.ResumeEntity)}.
   */
  public List<ResumeAnalysisEntity> findByResumeOrderByAnalyzedAtDesc(ResumeEntity resume) {
    String queryString = "SELECT r FROM ResumeAnalysisEntity r WHERE r.resume = :resume ORDER BY r.analyzedAt desc";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("resume", resume);

    return (List<ResumeAnalysisEntity>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link ResumeAnalysisRepository#findFirstByResumeIdOrderByAnalyzedAtDesc(java.lang.Long)}.
   */
  public ResumeAnalysisEntity findFirstByResumeIdOrderByAnalyzedAtDesc(Long resumeId) {
    String queryString = "SELECT r FROM ResumeAnalysisEntity r WHERE r.resume.id = :resumeId ORDER BY r.analyzedAt desc";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("resumeId", resumeId);
    if (query.getMaxResults() != Integer.MAX_VALUE) {
      if (query.getMaxResults() > 1 && query.getFirstResult() > 0) {
        query.setFirstResult(query.getFirstResult() - (query.getMaxResults() - 1));
      }
    }
    query.setMaxResults(1);

    return (ResumeAnalysisEntity) convertOne(query.getSingleResultOrNull(), false, ResumeAnalysisEntity.class);
  }
}
