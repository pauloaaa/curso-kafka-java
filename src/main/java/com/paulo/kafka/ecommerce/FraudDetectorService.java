package com.paulo.kafka.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.HashMap;

public class FraudDetectorService {

    public static void main(String[] args) {
        FraudDetectorService fraudService = new FraudDetectorService();
        try(KafkaService service = new KafkaService<Order>(FraudDetectorService.class.getSimpleName(),
                "ecommerce-new-order", fraudService::parse, Order.class, new HashMap<>())){
            service.run();
        }

    }

    private void parse(ConsumerRecord<String, Order> r) {
        System.out.println("-------------------------");
        System.out.println("Processing new order, checking for fraud");
        System.out.println(r.key());
        System.out.println(r.value());
        System.out.println(r.partition());
        System.out.println(r.offset());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Order processed");
    }
}
