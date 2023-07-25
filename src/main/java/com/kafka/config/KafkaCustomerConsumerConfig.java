package com.kafka.config;

import com.kafka.model.Customer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
// Import the correct JsonDeserializer class

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaCustomerConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    // Corrected the variable name from 'boostrapService' to 'bootstrapServers'

    @Bean
    public Map<String, Object> consumerCustomerConfig() {
        Map<String, Object> prop = new HashMap<>();
        prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers); // Corrected the config property name

        // Set the key and value deserializers
        prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class); // Changed to correct JsonDeserializer class

        return prop;
    }

    @Bean
    public ConsumerFactory<String, Customer> consumerCustomerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerCustomerConfig(), new StringDeserializer(), new JsonDeserializer<>(Customer.class)); // Use JsonDeserializer for the value
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Customer>> kafkaConsumerListenerContainerFactory(ConsumerFactory<String, Customer> consumerCustomerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, Customer> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerCustomerFactory);
        return factory;
    }
}
