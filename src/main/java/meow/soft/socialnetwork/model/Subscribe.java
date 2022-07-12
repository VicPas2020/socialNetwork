package meow.soft.socialnetwork.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
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
    @Setter(AccessLevel.NONE)
    @Column(name = "id", nullable = false)
    private UUID id;

    private String title;

    private String description;

    @ManyToMany(mappedBy = "subscribes")
    private Set<User> users = new HashSet<>();

    @Override
    public void update(Subscribe source) {
        this.description = source.getDescription();
        this.title = source.getTitle();
    }

    @Override
    public Subscribe createNewInstance() {
        Subscribe newSubscribe = new Subscribe();
        newSubscribe.update(this);

        return newSubscribe;
    }
}