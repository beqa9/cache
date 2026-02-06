package AI_project.cache.services;

import AI_project.cache.entities.Product;
import AI_project.cache.mappers.ProductMapper;
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
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "product-list")
    public List<ProductModel> getAllProducts() {
        return productMapper.toModelList(productRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "products", key = "#id")
    public ProductModel getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        return productMapper.toModel(product);
    }

    @Override
    @CachePut(value = "products", key = "#result.id")
    @CacheEvict(value = { "product-list", "product-search" }, allEntries = true)
    public ProductModel createProduct(ProductModel model) {
        Product product = productMapper.toEntity(model);
        Product saved = productRepository.save(product);
        return productMapper.toModel(saved);
    }

    @Override
    @CachePut(value = "products", key = "#id")
    @CacheEvict(value = { "product-list", "product-search" }, allEntries = true)
    public ProductModel updateProduct(Long id, ProductModel model) {
        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        product.setName(model.name());
        product.setDescription(model.description());
        product.setCategory(model.category());
        product.setPrice(model.price());
        product.setStockQuantity(model.stockQuantity());

        return productMapper.toModel(productRepository.save(product));
    }

    @Override
    @CacheEvict(value = { "products", "product-list", "product-search" }, key = "#id", allEntries = true)
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "product-search", key = "'name:' + #name")
    public List<ProductModel> searchByName(String name) {
        return productMapper.toModelList(
                productRepository.findByNameContainingIgnoreCase(name)
        );
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "product-search", key = "'category:' + #category")
    public List<ProductModel> searchByCategory(String category) {
        return productMapper.toModelList(
                productRepository.findByCategoryIgnoreCase(category)
        );
    }
}