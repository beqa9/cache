package AI_project.cache.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "products")
@SQLDelete(sql = "UPDATE products SET deleted = true WHERE id = ?")
@SQLRestriction("deleted = false")
public class Product extends BaseEntity {

    @Column(nullable = false)
    private String name;


    private String description;


    private String category;


    @Column(nullable = false)
    private BigDecimal price;


    @Column(name = "stock_quantity", nullable = false)
    private int stockQuantity;

}
