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
    private final ProductCacheService productCacheService;


    @Override
    @Transactional(readOnly = true)
    public List<ProductModel> getAllProducts() {
        return productMapper.toModelList(getAllProductsEntity());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductModel getProductById(Long id) {
        return productMapper.toModel(
                productCacheService.getProductEntityById(id)
        );
    }

    @Override
    @CacheEvict(value = { "products", "product-list", "product-search" }, allEntries = true)
    public ProductModel createProduct(ProductModel model) {
        Product product = productMapper.toEntity(model);
        Product saved = productRepository.save(product);
        return productMapper.toModel(saved);
    }

    @Override
    @CacheEvict(value = { "products", "product-list", "product-search" }, allEntries = true)
    public ProductModel updateProduct(Long id, ProductModel model) {
        Product product = getProductEntityById(id);
        product.setName(model.name());
        product.setDescription(model.description());
        product.setCategory(model.category());
        product.setPrice(model.price());
        product.setStockQuantity(model.stockQuantity());
        return productMapper.toModel(productRepository.save(product));
    }

    @Override
    @CacheEvict(value = { "products", "product-list", "product-search" }, allEntries = true)
    public void deleteProduct(Long id) {
        productRepository.delete(getProductEntityById(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductModel> searchByName(String name) {
        return productMapper.toModelList(searchByNameEntity(name));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductModel> searchByCategory(String category) {
        return productMapper.toModelList(searchByCategoryEntity(category));
    }

    @Cacheable(value = "product-list")
    public List<Product> getAllProductsEntity() {
        return productRepository.findAll();
    }

    @Cacheable(value = "products", key = "#id")
    public Product getProductEntityById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Product not found"
                ));
    }

    @Cacheable(value = "product-search", key = "'name:' + #name")
    public List<Product> searchByNameEntity(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    @Cacheable(value = "product-search", key = "'category:' + #category")
    public List<Product> searchByCategoryEntity(String category) {
        return productRepository.findByCategoryIgnoreCase(category);
    }
}