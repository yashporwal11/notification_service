package com.example.notificationService.NotificationService.helper;

import com.example.notificationService.NotificationService.entity.enums.StatusType;
import com.example.notificationService.NotificationService.entity.notificationService.Sms;
import com.example.notificationService.NotificationService.entity.thirdParty.ThirdPartyResponse;
import com.example.notificationService.NotificationService.service.notificationService.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateSmsFromThirdPartyResponse {

    @Autowired
    private NotificationService notificationService;

    public Sms update(Sms sms, ThirdPartyResponse thirdPartyResponse){

        sms.setFailureCode(thirdPartyResponse.getResponse().get(0).getCode());
        sms.setFailureComments(thirdPartyResponse.getResponse().get(0).getDescription());
        sms.setStatus(StatusType.PROCESSED);

        return notificationService.updateSms(sms);
    }
}
