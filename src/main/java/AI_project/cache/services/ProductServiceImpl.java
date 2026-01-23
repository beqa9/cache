package AI_project.cache.services;

import AI_project.cache.entities.Product;
import AI_project.cache.models.ProductModel;
import AI_project.cache.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "product-list")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "products", key = "#id")
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }


    @Override
    @Transactional
    @CachePut(value = "products", key = "#result.id")
    @CacheEvict(value = { "product-list", "product-search" }, allEntries = true)
    public Product createProduct(ProductModel model) {
        Product product = new Product();
        product.setName(model.name());
        product.setDescription(model.description());
        product.setCategory(model.category());
        product.setPrice(model.price());
        product.setStockQuantity(model.stockQuantity());
        return productRepository.save(product);
    }


    @Override
    @Transactional
    @CachePut(value = "products", key = "#id")
    @CacheEvict(value = { "product-list", "product-search" }, allEntries = true)
    public Product updateProduct(Long id, ProductModel model) {
        Product product = getProductById(id);
        product.setName(model.name());
        product.setDescription(model.description());
        product.setCategory(model.category());
        product.setPrice(model.price());
        product.setStockQuantity(model.stockQuantity());
        return productRepository.save(product);
    }

    @Override
    @Transactional
    @CacheEvict(value = { "products", "product-list", "product-search" }, key = "#id", allEntries = true)
    public void deleteProduct(Long id) {
        productRepository.delete(getProductById(id));
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "product-search", key = "'name:' + #name")
    public List<Product> searchByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "product-search", key = "'category:' + #category")
    public List<Product> searchByCategory(String category) {
        return productRepository.findByCategoryIgnoreCase(category);
    }
}
