package com.example.notificationService.NotificationService.service.kafkaService;

import com.example.notificationService.NotificationService.constant.ImiConstants;
import com.example.notificationService.NotificationService.entity.es.EsData;
import com.example.notificationService.NotificationService.entity.kafka.KafkaData;
import com.example.notificationService.NotificationService.entity.notificationService.Sms;
import com.example.notificationService.NotificationService.entity.imi.ImiData;
import com.example.notificationService.NotificationService.entity.imi.ImiResponse;
import com.example.notificationService.NotificationService.repository.EsRepository;
import com.example.notificationService.NotificationService.service.blacklistService.BlacklistService;
import com.example.notificationService.NotificationService.service.notificationService.NotificationService;
import com.example.notificationService.NotificationService.service.imiService.ImiService;
import com.example.notificationService.NotificationService.transformer.EsDataTransformer;
import com.example.notificationService.NotificationService.transformer.ImiDataTransformer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KafkaConsumer {

    private final BlacklistService blacklistService;
    private final ImiService imiService;
    private final NotificationService notificationService;
    private final EsRepository esRepository;
    private final ImiDataTransformer imiDataTransformer;
    private final EsDataTransformer esDataTransformer;

    @KafkaListener(topics = "notificationService", groupId = "notificationServiceGroup")
    public void consume(KafkaData kafkaData){

        log.info("KafkaData received is: " + kafkaData);

        Sms sms = notificationService.getSmsDetailsFromRequestId(kafkaData.getRequestId());

        //check blacklisted
        if(blacklistService.checkIfPhoneNumberIsBlacklisted(sms.getPhoneNumber())){
            log.info("phoneNumber is blacklisted");
            notificationService.updateSmsToBlacklisted(sms);
            return;
        }

        ImiData imiData = imiDataTransformer.transform(sms);

        ImiResponse imiResponse = imiService.sendToImi(imiData);

        if(imiResponse ==null || !imiResponse.getResponse().getClass().getSimpleName().equals("ArrayList") || !imiResponse.getResponse().get(0).getCode().equals(ImiConstants.RESPONSE_CODE)){
            log.info("imiData is not correct, therefore returned");
            return;
        }

        //updating sms
        sms = notificationService.updateSmsFromImiResponse(sms, imiResponse);

        //saving into ES
        EsData esData = esDataTransformer.transform(sms);

        esRepository.save(esData);
    }
}
