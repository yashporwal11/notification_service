package com.example.notificationService.NotificationService.transformer;

import com.example.notificationService.NotificationService.entity.es.EsData;
import com.example.notificationService.NotificationService.entity.notificationService.Sms;
import org.springframework.stereotype.Component;

@Component
public class EsDataTransformer {
    public EsData transform(Sms sms){
        return new EsData(sms.getId(),sms.getPhoneNumber(), sms.getMessage(), sms.getCreatedAt());
    }
}
