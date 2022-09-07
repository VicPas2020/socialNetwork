package meow.soft.socialnetwork.controllers;

import lombok.RequiredArgsConstructor;
import meow.soft.socialnetwork.model.User;
import meow.soft.socialnetwork.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/addSubscribe")
    public ResponseEntity<String> addSubscribe(String userId, String subscribeId) {
        if (userId.equals(subscribeId))
            return ResponseEntity.ok("subscribe added");

        userService.addSubscriber(UUID.fromString(userId), UUID.fromString(subscribeId));

        return ResponseEntity.ok("subscribe added");
    }

    @PostMapping("/removeSubscribe")
    public ResponseEntity<String> removeSubscribe(String userId, String subscribeId) {

        userService.removeSubscriber(UUID.fromString(userId), UUID.fromString(subscribeId));

        return ResponseEntity.ok("subscribe removed");
    }

    @GetMapping("")
    public ResponseEntity<Page<User>> getPage(Pageable pageable) {
        return ResponseEntity.ok(userService.getPage(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getOne(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.get(id));
    }

    @PutMapping("")
    public ResponseEntity<User> update(@RequestBody User updated) {
        return ResponseEntity.ok(userService.update(updated));
    }

    @PostMapping("")
    public ResponseEntity<User> create(@RequestBody User created) {
        return ResponseEntity.ok(userService.create(created));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        userService.delete(id);
        return ResponseEntity.ok("Ok");
    }
}
