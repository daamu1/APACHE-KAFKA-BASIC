package com.kafka.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.dto.MessageRequest;
import com.kafka.model.Customer;
import com.kafka.service.CustomerService;
import com.kafka.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kafka/m3")
public class MessageController {
    @Autowired
    KafkaProducerService kafkaProducerService;
    @Value("${spring.kafka.topic-name}")
    private String topic;
    @Autowired
    private CustomerService customerService;

    @Autowired(required = true)
    private KafkaTemplate<String, Customer> kafkaTemplate;
    @Autowired(required = true)
    private KafkaTemplate<String, String> kafkaCustomerTemplate;

    @PostMapping("/message")
    public void publishMessage(@RequestBody MessageRequest messageRequest) {
        kafkaCustomerTemplate.send("saurabh", messageRequest.message());
    }

    @PostMapping("/customer")
    public ResponseEntity<String> publishCustomer(@RequestBody Customer customer) {
        try {
            // Add the new customer to the database
            customerService.addNewCustomer(customer);
            kafkaTemplate.send(topic, customer);
            return ResponseEntity.ok("Customer published successfully to Kafka topic!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to publish customer to Kafka topic: " + e.getMessage());
        }
    }


    @GetMapping("/get-all")
    public ResponseEntity<String> fetchCustomersAndSendToKafka() {
        try {
            // Fetch Customers from the database
            List<Customer> customers = customerService.getAllCustomers();

            // Convert Customers to JSON strings
            List<String> jsonCustomers = customerService.getAllCustomersAsJson();

            // Send Customers to Kafka topic
            kafkaProducerService.sendCustomersToKafkaTopic(jsonCustomers);

            return ResponseEntity.ok("Customers sent to Kafka topic successfully!");
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to convert customers to JSON: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send customers to Kafka topic: " + e.getMessage());
        }
    }
}
