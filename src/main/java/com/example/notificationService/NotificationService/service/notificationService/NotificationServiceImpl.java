package com.example.notificationService.NotificationService.service.notificationService;

import com.example.notificationService.NotificationService.constant.BlacklistConstants;
import com.example.notificationService.NotificationService.constant.ResponseConstants;
import com.example.notificationService.NotificationService.entity.enums.StatusType;
import com.example.notificationService.NotificationService.entity.imi.ImiResponse;
import com.example.notificationService.NotificationService.entity.kafka.KafkaData;
import com.example.notificationService.NotificationService.entity.notificationService.Sms;
import com.example.notificationService.NotificationService.exception.NotFoundException;
import com.example.notificationService.NotificationService.repository.NotificationServiceRepository;
import com.example.notificationService.NotificationService.request.SmsRequest;
import com.example.notificationService.NotificationService.response.SmsResponse;
import com.example.notificationService.NotificationService.service.kafkaService.KafkaProducer;
import com.example.notificationService.NotificationService.transformer.KafkaDataTransformer;
import com.example.notificationService.NotificationService.transformer.SmsTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NotificationServiceImpl implements NotificationService {

    private final NotificationServiceRepository notificationServiceRepository;
    private final KafkaProducer kafkaProducer;
    private final SmsTransformer smsTransformer;
    private final KafkaDataTransformer kafkaDataTransformer;

    @Override
    public SmsResponse sendSMS(SmsRequest smsRequest) {

        //transform smsRequest to Sms
        Sms sms = smsTransformer.transform(smsRequest);

        //save in mysql
        sms = notificationServiceRepository.save(sms);

        //inserting in kafka
        KafkaData kafkaData = kafkaDataTransformer.transform(sms);
        kafkaProducer.sendMessage(kafkaData);

        return SmsResponse.builder().comments(ResponseConstants.SUCCESSFULLY_SENT).requestId(sms.getRequestId()).build();
    }

    @Override
    public Sms getSmsDetailsFromRequestId(String requestId) {

        Sms sms = notificationServiceRepository.findByRequestId(requestId);

        if (sms == null) {
            throw new NotFoundException("request_id not found");
        }

        return sms;
    }

    @Override
    public Sms updateSms(Sms sms) {
        return notificationServiceRepository.save(sms);
    }

    @Override
    public Sms updateSmsFromImiResponse(Sms sms, ImiResponse imiResponse) {

        sms.setFailureCode(imiResponse.getResponse().get(0).getCode());
        sms.setFailureComments(imiResponse.getResponse().get(0).getDescription());
        sms.setStatus(StatusType.PROCESSED);

        return this.updateSms(sms);
    }

    @Override
    public void updateSmsToBlacklisted(Sms sms) {

        sms.setStatus(StatusType.BLACKLISTED);
        sms.setFailureCode(BlacklistConstants.BLACKLIST_CODE);
        sms.setFailureComments(BlacklistConstants.BLACKLIST_COMMENT);

        this.updateSms(sms);
    }
}
