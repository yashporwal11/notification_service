package com.example.notificationService.NotificationService.transformer;

import com.example.notificationService.NotificationService.entity.enums.StatusType;
import com.example.notificationService.NotificationService.entity.notificationService.Sms;
import com.example.notificationService.NotificationService.request.SmsRequest;
import org.springframework.stereotype.Component;

@Component
public class SmsTransformer {
    public Sms transform(SmsRequest smsRequest){
        Sms sms = new Sms();

        sms.setPhoneNumber(smsRequest.getPhoneNumber());
        sms.setMessage(smsRequest.getMessage());
        sms.setStatus(StatusType.IN_PROGRESS);

        return sms;
    }
}
