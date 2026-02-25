package AI_project.cache.services;

public class ProductEvent {

    private Long productId;
    private String action;

    public ProductEvent() {
    }

    public ProductEvent(Long productId, String action) {
        this.productId = productId;
        this.action = action;
    }

    public Long getProductId() {
        return productId;
    }

    public String getAction() {
        return action;
    }
}