package interview.modules.interview.repository;

import interview.modules.interview.model.InterviewAnswerEntity;
import interview.modules.interview.model.InterviewSessionEntity;
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

/**
 * AOT generated JPA repository implementation for {@link InterviewAnswerRepository}.
 */
@Generated
public class InterviewAnswerRepositoryImpl__AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public InterviewAnswerRepositoryImpl__AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link InterviewAnswerRepository#findBySessionIdOrderByQuestionIndex(java.lang.Long)}.
   */
  public List<InterviewAnswerEntity> findBySessionIdOrderByQuestionIndex(Long sessionId) {
    String queryString = "SELECT i FROM InterviewAnswerEntity i WHERE i.session.id = :sessionId ORDER BY i.questionIndex asc";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("sessionId", sessionId);

    return (List<InterviewAnswerEntity>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link InterviewAnswerRepository#findBySessionOrderByQuestionIndex(interview.modules.interview.model.InterviewSessionEntity)}.
   */
  public List<InterviewAnswerEntity> findBySessionOrderByQuestionIndex(
      InterviewSessionEntity session) {
    String queryString = "SELECT i FROM InterviewAnswerEntity i WHERE i.session = :session ORDER BY i.questionIndex asc";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("session", session);

    return (List<InterviewAnswerEntity>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link InterviewAnswerRepository#findBySession_SessionIdAndQuestionIndex(java.lang.String,java.lang.Integer)}.
   */
  public Optional<InterviewAnswerEntity> findBySession_SessionIdAndQuestionIndex(String sessionId,
      Integer questionIndex) {
    String queryString = "SELECT i FROM InterviewAnswerEntity i LEFT JOIN i.session s WHERE s.sessionId = :sessionId AND i.questionIndex = :questionIndex";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("sessionId", sessionId);
    query.setParameter("questionIndex", questionIndex);

    return Optional.ofNullable((InterviewAnswerEntity) convertOne(query.getSingleResultOrNull(), false, InterviewAnswerEntity.class));
  }

  /**
   * AOT generated implementation of {@link InterviewAnswerRepository#findBySession_SessionIdOrderByQuestionIndex(java.lang.String)}.
   */
  public List<InterviewAnswerEntity> findBySession_SessionIdOrderByQuestionIndex(String sessionId) {
    String queryString = "SELECT i FROM InterviewAnswerEntity i LEFT JOIN i.session s WHERE s.sessionId = :sessionId ORDER BY i.questionIndex asc";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("sessionId", sessionId);

    return (List<InterviewAnswerEntity>) query.getResultList();
  }
}
