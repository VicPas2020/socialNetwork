package meow.soft.socialnetwork.controllers;

import meow.soft.socialnetwork.model.Subscribe;
import meow.soft.socialnetwork.repo.GenericRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subscribe")
public class SubscribeController extends GenericController<Subscribe> {
    public SubscribeController(GenericRepository<Subscribe> repository) {
        super(repository);
    }
}
