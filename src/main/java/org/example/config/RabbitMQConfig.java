package org.example.config;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQConfig {

    public static final String QUEUE_NAME = "demo_queue";
    public static final String HOST = "localhost";
    public static final String USERNAME = "guest";
    public static final String PASSWORD = "guest";

    public static final String EXCHANGE_NAME = "demo_exchange";
    public static final String ROUTING_KEY = "demo_queue";

    public static Connection getConnection() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(HOST);
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        return factory.newConnection();
    }
}
