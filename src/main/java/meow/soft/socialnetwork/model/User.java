package meow.soft.socialnetwork.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Cacheable
@Entity
@Table(name = "usr")
@SQLDelete(sql = "UPDATE usr SET is_deleted = true WHERE id=?")
@NamedEntityGraph(name = "User.subscribers", attributeNodes = @NamedAttributeNode("subscribers"))
@Where(clause = "is_deleted=false")
public class User extends SoftDelete {

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

    private String patronymic;

    private String sex;

    private LocalDate birthDate;

    private String city;

    private String photoUrl;

    private String about;

    private String nickName;

    private String skills;

    private String mail;

    private String phone;
    @OneToMany
    @JoinTable(name = "subscriptions",
            joinColumns = {@JoinColumn(name = "parent")},
            inverseJoinColumns = {@JoinColumn(name = "child")}
    )
    @JsonIgnore
    private Set<User> subscribers = new HashSet<>();


    public void update(User source) {
        BeanUtils.copyProperties(source, this);

    }

    public User createNewInstance() {
        User newUser = new User();
        newUser.update(this);

        return newUser;
    }

    public void addSubscriber(User user) {
        subscribers.add(user);
    }

    public void removeSubscriber(User user) {
        subscribers.remove(user);
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
