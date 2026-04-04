package interview.modules.user.repository;

import interview.modules.user.model.UserEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.lang.Long;
import java.lang.String;
import java.util.Optional;
import org.springframework.aot.generate.Generated;
import org.springframework.data.jpa.repository.aot.AotRepositoryFragmentSupport;
import org.springframework.data.jpa.repository.query.QueryEnhancerSelector;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;
import org.springframework.data.repository.query.Param;

/**
 * AOT generated JPA repository implementation for {@link UserRepository}.
 */
@Generated
public class UserRepositoryImpl__AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public UserRepositoryImpl__AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link UserRepository#existsByUsername(java.lang.String)}.
   */
  public boolean existsByUsername(String username) {
    String queryString = "SELECT u.id FROM UserEntity u WHERE u.username = :username";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("username", username);
    query.setMaxResults(1);

    return !query.getResultList().isEmpty();
  }

  /**
   * AOT generated implementation of {@link UserRepository#findByIdWithKnowledgeBases(java.lang.Long)}.
   */
  public Optional<UserEntity> findByIdWithKnowledgeBases(@Param("id") Long id) {
    String queryString = "SELECT u FROM UserEntity u LEFT JOIN FETCH u.knowledgeBases WHERE u.id = :id";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("id", id);

    return Optional.ofNullable((UserEntity) convertOne(query.getSingleResultOrNull(), false, UserEntity.class));
  }

  /**
   * AOT generated implementation of {@link UserRepository#findByUsername(java.lang.String)}.
   */
  public Optional<UserEntity> findByUsername(String username) {
    String queryString = "SELECT u FROM UserEntity u WHERE u.username = :username";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("username", username);

    return Optional.ofNullable((UserEntity) convertOne(query.getSingleResultOrNull(), false, UserEntity.class));
  }
}
