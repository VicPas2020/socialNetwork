package meow.soft.socialnetwork.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import meow.soft.socialnetwork.exceptions.CommonException;
import meow.soft.socialnetwork.exceptions.NotFoundException;
import meow.soft.socialnetwork.model.User;
import meow.soft.socialnetwork.repo.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public User get(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
    }


    public void addSubscriber(UUID userId, UUID subscribeID) {
        User user = get(userId);
        User subscriber = get(subscribeID);
        if (user.getSubscribers().stream().anyMatch(s -> s.getId().equals(subscribeID))) {
            throw new CommonException(String.format("User %s already have subscribe %s", userId, subscribeID));
        }

        user.addSubscriber(subscriber);
        update(user);

        log.debug("Subscriber with id {} add to user {}", subscribeID, userId);
    }

    public void removeSubscriber(UUID userId, UUID subscribeID) {
        User user = get(userId);
        User subscriber = get(subscribeID);
        if (user.getSubscribers().stream().anyMatch(s -> s.getId().equals(subscribeID))) {
            user.removeSubscriber(subscriber);
            update(user);
            log.debug("Subscribe with id {} removed from user {}", subscribeID, userId);
        }
    }

    @Transactional
    public User update(User updated) {
        User dbDomain = get(updated.getId());
        dbDomain.update(updated);
        return userRepository.save(updated);
    }

    @Transactional
    public User create(User user) {
        User newUser = user.createNewInstance();
        return userRepository.save(newUser);
    }

    @Transactional
    public void delete(UUID id) {
        //check if object with this id exists
        get(id);
        userRepository.deleteById(id);
    }

    public Page<User> getPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
