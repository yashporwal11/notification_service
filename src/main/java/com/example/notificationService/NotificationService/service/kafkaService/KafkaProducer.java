package com.example.notificationService.NotificationService.service.kafkaService;

import com.example.notificationService.NotificationService.entity.kafka.KafkaData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KafkaProducer {

    @Value("${kafka.topic-name}")
    private String topicName;

    private final KafkaTemplate<String, KafkaData> kafkaTemplate;

    public void sendMessage(KafkaData kafkaData){

        Message<KafkaData> message = MessageBuilder
                .withPayload(kafkaData)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();

        kafkaTemplate.send(message);
    }
}
