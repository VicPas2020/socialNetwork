package meow.soft.socialnetwork.model;

import java.util.UUID;

public interface GenericEntity<T> {

    void update(T source);

    UUID getId();

    T createNewInstance();
}
