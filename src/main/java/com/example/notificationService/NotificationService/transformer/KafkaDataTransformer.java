package com.example.notificationService.NotificationService.transformer;

import com.example.notificationService.NotificationService.entity.kafka.KafkaData;
import com.example.notificationService.NotificationService.entity.notificationService.Sms;
import org.springframework.stereotype.Component;

@Component
public class KafkaDataTransformer {
    public KafkaData transform(Sms sms){
        return new KafkaData(sms.getRequestId());
    }
}
