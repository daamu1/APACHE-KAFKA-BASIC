package com.kafka;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class SpringbootKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootKafkaApplication.class, args);
    }

    /*
    for using apache kafaka u should run few command in your terminal where you download kafka_2.13-3.5.0 project
    cd kafka_2.13-3.5.0/
     bin/zookeeper-server-start.sh config/zookeeper.properties
     bin/kafka-server-start.sh config/server.properties
    bin/kafka-console-consumer.sh --topic saurabh --from-beginning --bootstrap-server localhost:9092

     */
//    @Bean
//    CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate) {
//        return args -> {
//            for (int i = 0; i < 10; i++) {
//                kafkaTemplate.send("saurabh", "hello kafka i am here :)" + i);
//            }
//        };
//    }
}
