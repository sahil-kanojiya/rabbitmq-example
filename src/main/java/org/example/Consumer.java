package org.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.example.config.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.example.config.RabbitMQConfig.QUEUE_NAME;

public class Consumer {

    private static final Logger log = LoggerFactory.getLogger(Consumer.class);

    public static void main(String[] args) {
        try (Connection connection = RabbitMQConfig.getConnection();
             Channel channel = connection.createChannel()) {
            channel.basicConsume(
                    QUEUE_NAME,
                    true,
                    (consumerTag, delivery) -> {
                        String message = new String(delivery.getBody());
                        log.info("Consumer received: {}", message);
                    },
                    consumerTag -> log.info("consumer tag: {}", consumerTag)
            );
        } catch (Exception ex) {
            log.error("Ex occurred:", ex);
        }
    }
}
