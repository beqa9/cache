package AI_project.cache.services;

import AI_project.cache.entities.Product;
import AI_project.cache.models.ProductModel;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product createProduct(ProductModel model);

    Product updateProduct(Long id, ProductModel model);

    void deleteProduct(Long id);

    List<Product> searchByName(String name);

    List<Product> searchByCategory(String category);
}