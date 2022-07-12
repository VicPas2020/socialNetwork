package meow.soft.socialnetwork.service;

import meow.soft.socialnetwork.model.Subscribe;
import meow.soft.socialnetwork.repo.GenericRepository;
import org.springframework.stereotype.Service;

@Service
public class SubscribeService extends GenericService<Subscribe> {
    public SubscribeService(GenericRepository<Subscribe> repository) {
        super(repository);
    }
}
