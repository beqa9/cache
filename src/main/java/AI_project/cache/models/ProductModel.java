package AI_project.cache.models;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Builder
public record ProductModel(
        Long id,
        String name,
        String description,
        String category,
        BigDecimal price,
        int stockQuantity,
        OffsetDateTime createdAt
) {}