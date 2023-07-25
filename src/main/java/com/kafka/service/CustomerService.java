package com.kafka.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.model.Customer;
import com.kafka.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ObjectMapper objectMapper; // ObjectMapper to serialize to JSON

    public List<String> getAllCustomersAsJson() throws JsonProcessingException {
        List<Customer> customers = customerRepository.findAll();
        List<String> jsonCustomers = new ArrayList<>();

        for (Customer customer : customers) {
            String json = objectMapper.writeValueAsString(customer);
            jsonCustomers.add(json);
        }

        return jsonCustomers;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void addNewCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}
