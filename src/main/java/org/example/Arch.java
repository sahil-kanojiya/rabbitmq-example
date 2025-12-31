package org.example;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.example.config.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.example.config.RabbitMQConfig.EXCHANGE_NAME;
import static org.example.config.RabbitMQConfig.QUEUE_NAME;
import static org.example.config.RabbitMQConfig.ROUTING_KEY;

public class Arch {

    private static final Logger log = LoggerFactory.getLogger(Arch.class);

    public static void main(String[] args) {
        try (Connection connection = RabbitMQConfig.getConnection();
             Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT, true);

            channel.queueDeclare(QUEUE_NAME, true, false, false, null);

            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);

            log.info("Exchange, Queue and Binding declared successfully.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
