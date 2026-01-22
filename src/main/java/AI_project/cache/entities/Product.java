package AI_project.cache.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String name;


    private String description;


    private String category;


    @Column(nullable = false)
    private BigDecimal price;


    @Column(name = "stock_quantity", nullable = false)
    private int stockQuantity;


    @Column(name = "created_at", updatable = false)
    private OffsetDateTime createdAt;
}
