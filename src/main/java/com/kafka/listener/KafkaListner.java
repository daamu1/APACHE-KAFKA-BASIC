package com.kafka.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.model.Customer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class KafkaListner {
    @KafkaListener(topics = "saurabh", groupId = "groupId")
    void listner(String data) {
        System.out.println("Lister Received : " + data + " :)");
    }

    @KafkaListener(topics = "daam01", // Topic name from which to consume messages
            groupId = "786", // Group ID for this consumer
            containerFactory = "kafkaConsumerListenerContainerFactory" // Reference to the consumer factory bean
    )
    public void customerListener(Customer customer) {
        System.out.println("Listener Received: " + customer);
        // Add your message processing logic here
    }

    @KafkaListener(topics = "daam01", groupId = "786", containerFactory = "kafkaConsumerListenerContainerFactory")
    public void customerListener(String jsonCustomer) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Customer customer = objectMapper.readValue(jsonCustomer, Customer.class);
            System.out.println("Listener Received: " + customer);
            // Add your message processing logic here
        } catch (IOException e) {
            // Handle the deserialization exception
            e.printStackTrace();
        }
    }
}
