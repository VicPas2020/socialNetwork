package meow.soft.socialnetwork.repo;

import meow.soft.socialnetwork.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, UUID> {
    @EntityGraph(value = "User.subscribers")
    Page<User> findAll(Pageable pageable);

    @EntityGraph(value = "User.subscribers")
    Optional<User> findById(UUID id);
}
