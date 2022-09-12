package meow.soft.socialnetwork.integration.service;

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


    @BeforeEach
    public void init() {
        parent = UUID.fromString("bf0d7b78-9c84-4f16-8bab-b0f73c3e8e99");
        child = UUID.fromString("042119d0-fdd3-4f1b-ab35-7e33568a3494");
    }


    @Test
    void testGet() {
        User user = userService.get(parent);
        Assertions.assertNotNull(user);
    }

    @Test
    void testAddSubscriber() {
        userService.addSubscriber(parent, child);
    }

    @Test
    void testRemoveSubscriber() {
        userService.removeSubscriber(parent, child);
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
    }

    @Test
    void testGetPage() {
        Page<User> page = userService.getPage(Pageable.unpaged());
        Assertions.assertTrue(page.hasContent());
    }
}
