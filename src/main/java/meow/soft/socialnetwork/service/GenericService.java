package meow.soft.socialnetwork.service;

import meow.soft.socialnetwork.exceptions.NotFoundException;
import meow.soft.socialnetwork.model.GenericEntity;
import meow.soft.socialnetwork.repo.GenericRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public abstract class GenericService<T extends GenericEntity<T>> {

    private final GenericRepository<T> repository;

    public GenericService(GenericRepository<T> repository) {
        this.repository = repository;
    }

    public Page<T> getPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public T get(UUID id) {
        return repository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Object with id %s not found", id)));
    }

    @Transactional
    public T update(T updated) {
//        T dbDomain = get(updated.getId());
//        dbDomain.update(updated);

        return repository.save(updated);
    }

    @Transactional
    public T create(T newDomain) {
        T dbDomain = newDomain.createNewInstance();
        return repository.save(dbDomain);
    }

    @Transactional
    public void delete(UUID id) {
        //check if object with this id exists
        get(id);
        repository.deleteById(id);
    }
}