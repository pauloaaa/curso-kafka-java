package com.paulo.kafka.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.HashMap;

public class EmailService {

    public static void main(String[] args) {
        EmailService emailService = new EmailService();
        KafkaService service = new KafkaService(EmailService.class.getSimpleName(),
                "ecommerce-send-email", emailService::parse, String.class, new HashMap<>());
        service.run();
    }

    private void parse(ConsumerRecord<String, String> r) {
        System.out.println("-------------------------");
        System.out.println("Send email");
        System.out.println(r.key());
        System.out.println(r.value());
        System.out.println(r.partition());
        System.out.println(r.offset());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Email processed");
    }
}
