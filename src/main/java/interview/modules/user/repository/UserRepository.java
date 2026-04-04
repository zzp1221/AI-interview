package interview.modules.user.repository;

import interview.modules.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

    boolean existsByUsername(String username);

    @Query("SELECT u FROM UserEntity u LEFT JOIN FETCH u.knowledgeBases WHERE u.id = :id")
    Optional<UserEntity> findByIdWithKnowledgeBases(@Param("id") Long id);
}
