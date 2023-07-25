package com.kafka.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListner {
    @KafkaListener(topics = "saurabh",groupId = "groupId")
    void listner(String data)
    {
        System.out.println("Lister Received : "+data+" :)");
    }
}
