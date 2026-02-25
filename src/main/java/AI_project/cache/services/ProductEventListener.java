package AI_project.cache.services;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductEventListener {

    private final CacheManager cacheManager;

    @RabbitListener(queues = "product.queue")
    public void handleProductEvent(ProductEvent event) {

        if (cacheManager.getCache("products") != null) {
            cacheManager.getCache("products").evict(event.getProductId());
        }

        System.out.println("Handled event: " + event.getAction());
    }
}