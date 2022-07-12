package meow.soft.socialnetwork.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "usr")
@SQLDelete(sql = "UPDATE usr SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
public class User extends SoftDelete implements GenericEntity<User> {

    @Setter(AccessLevel.NONE)
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    private String firstName;

    private String lastName;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "usr_subscribes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "subscribe_id"))
    private List<Subscribe> subscribes = new ArrayList<>();

    @Override
    public void update(User source) {
        this.firstName = source.getFirstName();
        this.lastName = source.getLastName();
    }

    @Override
    public User createNewInstance() {
        User newUser = new User();
        newUser.update(this);

        return newUser;
    }

    public void addSubscribe(Subscribe subscribe) {
        subscribes.add(subscribe);
        subscribe.getUsers().add(this);
    }

    public void removeSubscribe(Subscribe subscribe) {
        subscribes.remove(subscribe);
        subscribe.getUsers().remove(this);
    }
}
