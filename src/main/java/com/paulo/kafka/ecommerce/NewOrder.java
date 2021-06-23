package com.paulo.kafka.ecommerce;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrder {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try (KafkaDispatcher orderDispatcher = new KafkaDispatcher<Order>()) {
            try (KafkaDispatcher emailDispatcher = new KafkaDispatcher<Email>()) {
                for (int i = 0; i < 10; i++) {

                    String userId = UUID.randomUUID().toString();
                    String orderId = UUID.randomUUID().toString();
                    BigDecimal amount = new BigDecimal(Math.random() * 5000 + 1);
                    Order order = new Order(userId, orderId, amount);
                    orderDispatcher.send("ecommerce-new-order", userId, order);
                    String email = "Thank you for your order! We are processing your order!";
                    emailDispatcher.send("ecommerce-send-email", userId, email);

                }
            }
        }
    }
}
