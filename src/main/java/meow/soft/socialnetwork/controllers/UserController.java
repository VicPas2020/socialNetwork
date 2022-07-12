package meow.soft.socialnetwork.controllers;

import meow.soft.socialnetwork.model.User;
import meow.soft.socialnetwork.repo.GenericRepository;
import meow.soft.socialnetwork.service.GenericService;
import meow.soft.socialnetwork.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController extends GenericController<User> {
    @Autowired
    UserService userService;
    public UserController(GenericRepository<User> repository) {
        super(repository);
    }

    @PostMapping("/addSubscribe")
    public ResponseEntity addSubscribe(String userId, String subscribeId) {

        userService.addSubscribe(UUID.fromString(userId), UUID.fromString(subscribeId));

        return ResponseEntity.ok("subscribe added");
    }

    @PostMapping("/removeSubscribe")
    public ResponseEntity removeSubscribe(String userId, String subscribeId) {

        userService.removeSubscribe(UUID.fromString(userId), UUID.fromString(subscribeId));

        return ResponseEntity.ok("subscribe removed");
    }


//    @Override
//    protected UserService getService() {
//        GenericService<User> service = super.getService();
//        return (UserService) service;
//    }
}
