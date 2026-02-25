package AI_project.cache.configs;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String PRODUCT_EXCHANGE = "product.exchange";
    public static final String PRODUCT_QUEUE = "product.queue";
    public static final String PRODUCT_ROUTING_KEY = "product.routingKey";

    @Bean
    public TopicExchange productExchange() {
        return new TopicExchange(PRODUCT_EXCHANGE);
    }

    @Bean
    public Queue productQueue() {
        return new Queue(PRODUCT_QUEUE);
    }

    @Bean
    public Binding productBinding() {
        return BindingBuilder
                .bind(productQueue())
                .to(productExchange())
                .with(PRODUCT_ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
