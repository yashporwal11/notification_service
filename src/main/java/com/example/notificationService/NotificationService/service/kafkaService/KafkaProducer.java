package com.example.notificationService.NotificationService.service.kafkaService;

import com.example.notificationService.NotificationService.entity.kafka.KafkaData;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private final KafkaTemplate<String, KafkaData> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, KafkaData> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(KafkaData kafkaData){

        Message<KafkaData> message = MessageBuilder
                .withPayload(kafkaData)
                .setHeader(KafkaHeaders.TOPIC, "myTopicc")
                .build();

        kafkaTemplate.send(message);
    }
}
