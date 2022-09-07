package meow.soft.socialnetwork.service;

import meow.soft.socialnetwork.exceptions.NotFoundException;
import meow.soft.socialnetwork.model.User;
import meow.soft.socialnetwork.repo.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGet() {
        Optional<User> mock = Optional.of(mock(User.class));
        when(userRepository.findById(any())).thenReturn(mock);

        User result = userService.get(null);
        Assertions.assertEquals(mock.get(), result);
    }

    @Test
    void testAddSubscriber() {
        Optional<User> mock = Optional.of(mock(User.class));
        when(userRepository.findById(any())).thenReturn(mock);

        userService.addSubscriber(null, null);
    }

    @Test
    void testRemoveSubscriber() {
        Optional<User> mock = Optional.of(mock(User.class));
        when(userRepository.findById(any())).thenReturn(mock);

        userService.removeSubscriber(null, null);
    }

    @Test
    void testUpdate() {
        Optional<User> mock = Optional.of(mock(User.class));
        when(userRepository.findById(any())).thenReturn(mock);
        when(userRepository.save(any())).thenReturn(mock.get());

        User result = userService.update(mock.get());
        Assertions.assertEquals(mock.get(), result);
    }

    @Test
    void testCreate() {
        User user = Mockito.mock(User.class);
        when(userRepository.save(any())).thenReturn(user);
        User result = userService.create(user);
        Assertions.assertEquals(user, result);
    }

    @Test
    void testDelete() {
        Optional<User> mock = Optional.of(mock(User.class));
        when(userRepository.findById(any())).thenReturn(mock);

        userService.delete(null);
    }

    @Test
    void testGetPage() {
        when(userRepository.findAll(any())).thenReturn(null);

        Page<User> result = userService.getPage(null);
        Assertions.assertEquals(null, result);
    }

    @Test
    void testNotFound() {
        NotFoundException notFoundException = Assertions.assertThrows(NotFoundException.class, () -> userService.get(null));
        Assertions.assertEquals("User not found", notFoundException.getMessage());
    }
}