package meow.soft.socialnetwork.integration.service;

import meow.soft.socialnetwork.exceptions.NotFoundException;
import meow.soft.socialnetwork.model.User;
import meow.soft.socialnetwork.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;

import java.util.UUID;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class UserServiceTest {
    @Autowired
    UserService userService;

    UUID parent;
    UUID child;

    UUID notSubscribed;


    @BeforeEach
    public void init() {
        child = UUID.fromString("bf0d7b78-9c84-4f16-8bab-b0f73c3e8e99");
        parent = UUID.fromString("042119d0-fdd3-4f1b-ab35-7e33568a3494");
        notSubscribed = UUID.fromString("a7b520b7-0195-4f32-ad2e-3eb40a5a645c");
    }


    @Test
    void testGet() {
        User user = userService.get(parent);
        Assertions.assertNotNull(user);
    }

    @Test
    void testAddSubscriber() {
        userService.addSubscriber(parent, child);
        boolean match = userService.get(parent).getSubscribers().stream().anyMatch(s -> s.getId().equals(child));
        Assertions.assertTrue(match);
    }

    @Test
    void testRemoveSubscriber() {
        boolean removeSubscriber = userService.removeSubscriber(parent, child);
        Assertions.assertTrue(removeSubscriber);
    }

    @Test
    void testRemoveSubscriberFailed() {
        boolean removeSubscriber = userService.removeSubscriber(parent, notSubscribed);
        Assertions.assertFalse(removeSubscriber);
    }

    @Test
    void testUpdate() {
        User user = userService.get(parent);
        user.setCity("TEST CITY");
        User update = userService.update(user);

        Assertions.assertEquals(user.getCity(), update.getCity());

    }

    @Test
    void testCreate() {
        User user = new User();
        user.setCity("TEST CITY");

        User created = userService.create(user);
        Assertions.assertEquals(user.getCity(), created.getCity());
    }

    @Test
    void testDelete() {
        userService.delete(child);
        Assertions.assertThrowsExactly(NotFoundException.class, () -> userService.get(child));
    }

    @Test
    void testGetPage() {
        Page<User> page = userService.getPage(Pageable.unpaged());
        Assertions.assertTrue(page.hasContent());
    }
}
