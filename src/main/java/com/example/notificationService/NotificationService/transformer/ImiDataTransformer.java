package com.example.notificationService.NotificationService.transformer;

import com.example.notificationService.NotificationService.constant.ImiConstants;
import com.example.notificationService.NotificationService.entity.notificationService.Sms;
import com.example.notificationService.NotificationService.entity.imi.Channels;
import com.example.notificationService.NotificationService.entity.imi.DestinationData;
import com.example.notificationService.NotificationService.entity.imi.ImiData;
import com.example.notificationService.NotificationService.entity.imi.SmsImi;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ImiDataTransformer {
    public ImiData transform(Sms sms){

        SmsImi smsImi = SmsImi.builder().text(sms.getMessage()).build();
        Channels channels = Channels.builder().smsImi(smsImi).build();

        List<String> msisdn = new ArrayList<>();
        msisdn.add(sms.getPhoneNumber());
        DestinationData destinationData = DestinationData.builder().msisdn(msisdn).correlationId(sms.getRequestId()).build();
        List<DestinationData> destination = new ArrayList<>();
        destination.add(destinationData);

        return ImiData.builder().deliveryChannel(ImiConstants.DELIVERY_CHANNEL).channels(channels).destination(destination).build();
    }
}
