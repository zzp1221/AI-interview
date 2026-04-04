package interview.modules.resume.repository;

import interview.modules.resume.model.ResumeEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.lang.String;
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
}
