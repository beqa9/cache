package AI_project.cache.services;

import AI_project.cache.entities.Product;
import AI_project.cache.models.ProductModel;

import java.util.List;

public interface ProductService {

    List<ProductModel> getAllProducts();

    ProductModel getProductById(Long id);

    ProductModel createProduct(ProductModel model);

    ProductModel updateProduct(Long id, ProductModel model);

    void deleteProduct(Long id);

    List<ProductModel> searchByName(String name);

    List<ProductModel> searchByCategory(String category);
}
