package com.example.notificationService.NotificationService.service.notificationService;

import com.example.notificationService.NotificationService.entity.kafka.KafkaData;
import com.example.notificationService.NotificationService.entity.notificationService.Sms;
import com.example.notificationService.NotificationService.exception.NotFoundException;
import com.example.notificationService.NotificationService.repository.NotificationServiceRepository;
import com.example.notificationService.NotificationService.request.SmsRequest;
import com.example.notificationService.NotificationService.service.kafkaService.KafkaProducer;
import com.example.notificationService.NotificationService.transformer.KafkaDataTransformer;
import com.example.notificationService.NotificationService.transformer.SmsTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    private NotificationServiceRepository notificationServiceRepository;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private SmsTransformer smsTransformer;

    @Autowired
    private KafkaDataTransformer kafkaDataTransformer;

    public NotificationServiceImpl(){}

    @Override
    public Sms sendSMS(SmsRequest smsRequest) {

        //transform smsRequest to Sms
        Sms sms = smsTransformer.transform(smsRequest);

        //save in mysql
        sms = notificationServiceRepository.save(sms);

        //inserting in kafka
        KafkaData kafkaData = kafkaDataTransformer.transform(sms);
        kafkaProducer.sendMessage(kafkaData);

        return sms;
    }

    @Override
    public Sms getSmsDetailsFromRequestId(String requestId) {

        Sms sms = notificationServiceRepository.findByRequestId(requestId);

        if(sms==null){
            throw new NotFoundException("request_id not found");
        }
        return sms;
    }

    @Override
    public Sms updateSms(Sms sms) {
        return notificationServiceRepository.save(sms);
    }

}
