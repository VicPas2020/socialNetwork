package meow.soft.socialnetwork.repo;

import meow.soft.socialnetwork.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;

public interface UserRepository extends GenericRepository<User> {
    @EntityGraph(attributePaths = { "subscribes" })
    Page<User> findAll(Pageable pageable);
}
