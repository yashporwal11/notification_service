package com.example.notificationService.NotificationService.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${tpd.topic-name}")
    private String topicName;

    @Bean
    public NewTopic kafkaTopic(){
        return TopicBuilder.name(topicName)
        .build();
    }
}
