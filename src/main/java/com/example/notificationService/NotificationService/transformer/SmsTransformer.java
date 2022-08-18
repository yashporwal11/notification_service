package com.example.notificationService.NotificationService.transformer;

import com.example.notificationService.NotificationService.entity.enums.StatusType;
import com.example.notificationService.NotificationService.entity.notificationService.Sms;
import com.example.notificationService.NotificationService.request.SmsRequest;
import org.springframework.stereotype.Component;

@Component
public class SmsTransformer {
    public Sms transform(SmsRequest smsRequest){
        return Sms.builder().phoneNumber(smsRequest.getPhoneNumber()).message(smsRequest.getMessage()).status(StatusType.IN_PROGRESS).build();
    }
}
