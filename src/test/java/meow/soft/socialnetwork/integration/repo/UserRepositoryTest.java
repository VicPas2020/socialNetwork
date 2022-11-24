package meow.soft.socialnetwork.integration.repo;

import meow.soft.socialnetwork.model.User;
import meow.soft.socialnetwork.repo.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;


    @Test
    void testFindById() {
        Optional<User> byId = userRepository.findById(UUID.fromString("042119d0-fdd3-4f1b-ab35-7e33568a3494"));
        Assertions.assertTrue(byId.isPresent());
    }

    @Test
    @Transactional
    void testCleanSubscribers() {
        UUID child = UUID.fromString("bf0d7b78-9c84-4f16-8bab-b0f73c3e8e99");
        userRepository.cleanSubscribers(child);
        Optional<User> byId = userRepository.findById(UUID.fromString("042119d0-fdd3-4f1b-ab35-7e33568a3494"));
        Assertions.assertTrue(byId.isPresent());
        Assertions.assertTrue(byId.get().getSubscribers().isEmpty());
    }

    @Test
    void testFindAll() {
        Page<User> all = userRepository.findAll(Pageable.unpaged());
        Assertions.assertEquals(3L, all.getTotalElements());
    }
}

