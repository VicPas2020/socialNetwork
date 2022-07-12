package meow.soft.socialnetwork.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public abstract class SoftDelete {
    @Column(name = "is_deleted")
    private boolean isDeleted = false;
}
