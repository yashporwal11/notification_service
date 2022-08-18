package com.example.notificationService.NotificationService.service.notificationService;

import com.example.notificationService.NotificationService.entity.imi.ImiResponse;
import com.example.notificationService.NotificationService.entity.notificationService.Sms;
import com.example.notificationService.NotificationService.request.SmsRequest;
import com.example.notificationService.NotificationService.response.SmsResponse;
import org.springframework.stereotype.Service;

@Service
public interface NotificationService {
    SmsResponse sendSMS(SmsRequest smsRequest);

    Sms getSmsDetailsFromRequestId(String requestId);

    Sms updateSms(Sms sms);

    Sms updateSmsFromImiResponse(Sms sms, ImiResponse imiResponse);

    void updateSmsToBlacklisted(Sms sms);
}
