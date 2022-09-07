package meow.soft.socialnetwork.repo;

import meow.soft.socialnetwork.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
    @EntityGraph(value = "User.subscribers")
    Page<User> findAll(Pageable pageable);

    @EntityGraph(value = "User.subscribers")
    Optional<User> findById(UUID id);

    @Modifying
    @Query(value = "delete from subscriptions where child = :id", nativeQuery = true)
    void cleanSubscribers(@Param("id") UUID id);
}
