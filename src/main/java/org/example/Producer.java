package org.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.example.config.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.example.config.RabbitMQConfig.EXCHANGE_NAME;
import static org.example.config.RabbitMQConfig.ROUTING_KEY;

public class Producer {

    private static final Logger log = LoggerFactory.getLogger(Producer.class);

    public static void main(String[] args) {
        try (Connection connection = RabbitMQConfig.getConnection();
             Channel channel = connection.createChannel()) {
            for (int i = 0; i < 10; i++) {
                final String message = "hello world: " + i;
                channel.basicPublish(
                        EXCHANGE_NAME,
                        ROUTING_KEY,
                        null,
                        message.getBytes()
                );
                log.info("Producer sent: {}", message);
            }
        } catch (Exception ex) {
            log.error("Ex occurred:", ex);
        }
    }
}
