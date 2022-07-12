package meow.soft.socialnetwork.controllers;

import meow.soft.socialnetwork.model.User;
import meow.soft.socialnetwork.repo.GenericRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController extends GenericController<User>{

    public UserController(GenericRepository<User> repository) {
        super(repository);
    }

    
}
