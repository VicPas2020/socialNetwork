package meow.soft.socialnetwork.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Cacheable
@Entity
@Table(name = "usr")
@SQLDelete(sql = "UPDATE usr SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
@NamedEntityGraph(name = "User.subscribes", attributeNodes = @NamedAttributeNode("subscribes"))
public class User extends SoftDelete implements GenericEntity<User> {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String firstName;

    private String lastName;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "usr_subscribes",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "subscribe_id", referencedColumnName = "id"))
    private Set<Subscribe> subscribes = new HashSet<>();

    @Override
    public void update(User source) {
        this.firstName = source.getFirstName();
        this.lastName = source.getLastName();
        this.subscribes = source.subscribes;
    }

    @Override
    public User createNewInstance() {
        User newUser = new User();
        newUser.update(this);

        return newUser;
    }

    public void addSubscribe(Subscribe subscribe) {
        subscribes.add(subscribe);
    }

    public void removeSubscribe(Subscribe subscribe) {
        subscribes.remove(subscribe);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
