package meow.soft.socialnetwork.service;

import meow.soft.socialnetwork.exceptions.CommonException;
import meow.soft.socialnetwork.model.Subscribe;
import meow.soft.socialnetwork.model.User;
import meow.soft.socialnetwork.repo.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService extends GenericService<User> {
    @Autowired
    private SubscribeService subscribeService;

    public UserService(GenericRepository<User> repository) {
        super(repository);
    }

    public void addSubscribe(UUID userId, UUID subscribeID) {
        User user = get(userId);
        if (user.getSubscribes().stream().anyMatch(s -> s.getId().equals(subscribeID))) {
            throw new CommonException(String.format("User %s already have subscribe %s", userId, subscribeID));
        }
        Subscribe subscribe = subscribeService.get(subscribeID);
        user.addSubscribe(subscribe);
        update(user);
    }

    public void removeSubscribe(UUID userId, UUID subscribeID) {
        User user = get(userId);
        if (user.getSubscribes().stream().anyMatch(s -> s.getId().equals(subscribeID))) {
            Subscribe subscribe = subscribeService.get(subscribeID);

            user.removeSubscribe(subscribe);
            update(user);
        }
    }
}
