package meow.soft.socialnetwork.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "subscribe")
@SQLDelete(sql = "UPDATE subscribe SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
public class Subscribe extends SoftDelete implements GenericEntity<Subscribe> {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String title;

    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "subscribes")
    private Set<User> users = new HashSet<>();

    @Override
    public void update(Subscribe source) {
        this.description = source.getDescription();
        this.title = source.getTitle();
        this.users = source.getUsers();
    }

    @Override
    public Subscribe createNewInstance() {
        Subscribe newSubscribe = new Subscribe();
        newSubscribe.update(this);

        return newSubscribe;
    }
}