package meow.soft.socialnetwork.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import meow.soft.socialnetwork.exceptions.CommonException;
import meow.soft.socialnetwork.exceptions.NotFoundException;
import meow.soft.socialnetwork.model.User;
import meow.soft.socialnetwork.model.UserDto;
import meow.soft.socialnetwork.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
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

        try {
            userService.addSubscriber(UUID.fromString(userId), UUID.fromString(subscribeId));
        }
        catch (NotFoundException notFoundException) {
            return ResponseEntity.status(404).body(notFoundException.getMessage());
        }
        catch (CommonException commonException) {
            return ResponseEntity.status(500).body(commonException.getMessage());
        }

        return ResponseEntity.ok("subscribe added");
    }

    @PostMapping("/removeSubscribe")
    public ResponseEntity<String> removeSubscribe(String userId, String subscribeId) {

        try {
            userService.removeSubscriber(UUID.fromString(userId), UUID.fromString(subscribeId));
        }
        catch (NotFoundException notFoundException) {
            return ResponseEntity.status(404).body(notFoundException.getMessage());
        }
        catch (CommonException commonException) {
            return ResponseEntity.status(500).body(commonException.getMessage());
        }

        return ResponseEntity.ok("subscribe removed");
    }

    @Operation(summary = "Get a list of users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found users",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "Users not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal error",
                    content = @Content)})
    @GetMapping("")
    public ResponseEntity<Page<User>> getPage(Pageable pageable) {
        return ResponseEntity.ok(userService.getPage(pageable));
    }

    @Operation(summary = "Get an user by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal error",
                    content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<User> getOne(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.get(id));
    }

    @Operation(summary = "Update user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDto.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal error",
                    content = @Content)})
    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> update(@PathVariable("id") UUID id, @RequestBody UserDto updated) {
        if(!Objects.equals(id, updated.getId())){
            return ResponseEntity.status(500).body("IDs don't match");
        }
        User existing = userService.get(id);
        User user = new User();
        BeanUtils.copyProperties(updated, user);
        user.setSubscribers(existing.getSubscribers());
        userService.update(user);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Create new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal error",
                    content = @Content)})
    @PostMapping("")
    public ResponseEntity<User> create(@RequestBody UserDto created) {
        User user = new User();
        BeanUtils.copyProperties(created, user);
        User saved = userService.create(user);
        return ResponseEntity.ok(saved);
    }

    @Operation(summary = "Delete user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal error",
                    content = @Content)})
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        userService.delete(id);
        return ResponseEntity.ok("deleted");
    }
}
