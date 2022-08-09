package com.example.notificationService.NotificationService.transformer;

import com.example.notificationService.NotificationService.entity.notificationService.Sms;
import com.example.notificationService.NotificationService.entity.thirdParty.Channels;
import com.example.notificationService.NotificationService.entity.thirdParty.DestinationData;
import com.example.notificationService.NotificationService.entity.thirdParty.SmsThirdParty;
import com.example.notificationService.NotificationService.entity.thirdParty.ThirdPartyData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ThirdPartyDataTransformer {
    public ThirdPartyData transform(Sms sms){

        String deliveryChannel = "sms";

        SmsThirdParty smsThirdParty = new SmsThirdParty();
        smsThirdParty.setText(sms.getMessage());
        Channels channels = new Channels(smsThirdParty);

        List<String> msisdn = new ArrayList<>();
        msisdn.add(sms.getPhoneNumber());
        String correlationId = sms.getRequestId();
        DestinationData destinationData = new DestinationData(msisdn,correlationId);
        List<DestinationData> destination = new ArrayList<>();
        destination.add(destinationData);

        return new ThirdPartyData(deliveryChannel,channels,destination);
    }
}
