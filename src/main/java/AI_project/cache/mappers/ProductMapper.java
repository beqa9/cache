package AI_project.cache.mappers;

import AI_project.cache.entities.Product;
import AI_project.cache.models.ProductModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductModel toModel(Product product);

    List<ProductModel> toModelList(List<Product> products);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Product toEntity(ProductModel model);
}