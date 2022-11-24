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
import org.springframework.data.domain.Page;

import java.util.*;

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
        User userMock = mock(User.class);
        Optional<User> mock = Optional.of(userMock);
        when(userRepository.findById(any())).thenReturn(mock);
        doCallRealMethod().when(userMock).addSubscriber(any());
        doReturn(new HashSet<User>()).when(userMock).getSubscribers();

        userService.addSubscriber(null, null);
        Assertions.assertEquals(1, userMock.getSubscribers().size());
    }

    @Test
    void testRemoveSubscriber() {
        User userMock = mock(User.class);
        Optional<User> mock = Optional.of(userMock);
        when(userRepository.findById(any())).thenReturn(mock);
        doCallRealMethod().when(userMock).removeSubscriber(any());

        UUID uuid = UUID.randomUUID();
        User user = new User();
        user.setId(uuid);

        doReturn(new HashSet<>(List.of(user))).when(userMock).getSubscribers();

        boolean removeSubscriber = userService.removeSubscriber(null, uuid);
        Assertions.assertTrue(removeSubscriber);
    }

    @Test
    void testRemoveSubscriberFailed() {
        Optional<User> mock = Optional.of(mock(User.class));
        when(userRepository.findById(any())).thenReturn(mock);

        boolean removeSubscriber = userService.removeSubscriber(null, null);
        Assertions.assertFalse(removeSubscriber);
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
        UUID uuid = UUID.randomUUID();
        when(userRepository.findById(uuid)).thenReturn(mock);
        doNothing().when(userRepository).cleanSubscribers(uuid);
        doNothing().when(userRepository).deleteById(uuid);
        userService.delete(uuid);

        verify(userRepository, times(1)).cleanSubscribers(uuid);
        verify(userRepository, times(1)).deleteById(uuid);
    }

    @Test
    void testGetPage() {
        when(userRepository.findAll(any())).thenReturn(null);

        Page<User> result = userService.getPage(null);
        Assertions.assertNull(result);
    }

    @Test
    void testNotFound() {
        NotFoundException notFoundException = Assertions.assertThrows(NotFoundException.class, () -> userService.get(null));
        Assertions.assertEquals("User null not found", notFoundException.getMessage());
    }
}