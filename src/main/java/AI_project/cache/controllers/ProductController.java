package AI_project.cache.controllers;

import AI_project.cache.mappers.ProductMapper;
import AI_project.cache.models.ProductModel;
import AI_project.cache.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductModel> getAll() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductModel getById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public ProductModel create(@RequestBody ProductModel model) {
        return productService.createProduct(model);
    }

    @PutMapping("/{id}")
    public ProductModel update(
            @PathVariable Long id,
            @RequestBody ProductModel model
    ) {
        return productService.updateProduct(id, model);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/search")
    public List<ProductModel> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category
    ) {
        if (name != null) {
            return productService.searchByName(name);
        }

        if (category != null) {
            return productService.searchByCategory(category);
        }

        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Either 'name' or 'category' must be provided"
        );
    }
}