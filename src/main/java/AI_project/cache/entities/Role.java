package AI_project.cache.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "roles")
@Getter
@Setter
@SQLDelete(sql = "UPDATE products SET deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
public class Role extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String name;
}