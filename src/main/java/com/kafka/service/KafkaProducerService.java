package com.kafka.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
// ...

@Service
public class KafkaProducerService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private CustomerService customerService;

    public void sendCustomersToKafkaTopic(List<String> jsonCustomers) throws JsonProcessingException {
        List<String> jsonCustomerss = customerService.getAllCustomersAsJson();
        for (String jsonCustomer : jsonCustomers) {
            kafkaTemplate.send("daam01", jsonCustomer);
        }
    }
}
