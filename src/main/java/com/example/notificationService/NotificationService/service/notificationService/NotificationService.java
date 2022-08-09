package com.example.notificationService.NotificationService.service.notificationService;

import com.example.notificationService.NotificationService.entity.notificationService.Sms;
import com.example.notificationService.NotificationService.request.SmsRequest;
import org.springframework.stereotype.Service;

@Service
public interface NotificationService {
    Sms sendSMS(SmsRequest smsRequest);

    Sms getSmsDetailsFromRequestId(String requestId);

    Sms updateSms(Sms sms);
}
