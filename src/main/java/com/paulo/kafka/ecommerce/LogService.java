package com.paulo.kafka.ecommerce;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;


import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class LogService {

    public static void main(String[] args) {
        LogService logService = new LogService();

        Map<String, String> map = new HashMap<>();
        map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getSimpleName());

        try(KafkaService service = new KafkaService(LogService.class.getSimpleName(),
                Pattern.compile("ecommerce.*"),
                logService::parse, String.class,
                map)){
            service.run();
        }
    }

    private void parse(ConsumerRecord<String, String> r) {
        System.out.println("-------------------------");
        System.out.println("LOG: " + r.topic());
        System.out.println(r.key());
        System.out.println(r.value());
        System.out.println(r.partition());
        System.out.println(r.offset());
    }
}
