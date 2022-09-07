package meow.soft.socialnetwork.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal error",
                    content = @Content)})
    @PutMapping("")
    public ResponseEntity<User> update(@RequestBody User updated) {
        return ResponseEntity.ok(userService.update(updated));
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
    public ResponseEntity<User> create(@RequestBody User created) {
        return ResponseEntity.ok(userService.create(created));
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
