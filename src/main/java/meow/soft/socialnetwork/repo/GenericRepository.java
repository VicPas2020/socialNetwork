package meow.soft.socialnetwork.repo;

import meow.soft.socialnetwork.model.GenericEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepository<T extends GenericEntity<T>> extends JpaRepository<T, Long> {
}
