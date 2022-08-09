package com.example.notificationService.NotificationService.service.kafkaService;

import com.example.notificationService.NotificationService.entity.es.EsData;
import com.example.notificationService.NotificationService.entity.kafka.KafkaData;
import com.example.notificationService.NotificationService.entity.notificationService.Sms;
import com.example.notificationService.NotificationService.entity.thirdParty.ThirdPartyData;
import com.example.notificationService.NotificationService.entity.thirdParty.ThirdPartyResponse;
import com.example.notificationService.NotificationService.helper.UpdateSmsFromThirdPartyResponse;
import com.example.notificationService.NotificationService.repository.EsRepository;
import com.example.notificationService.NotificationService.service.blacklistService.BlacklistService;
import com.example.notificationService.NotificationService.service.notificationService.NotificationService;
import com.example.notificationService.NotificationService.service.thirdPartyService.ThirdPartyService;
import com.example.notificationService.NotificationService.transformer.EsDataTransformer;
import com.example.notificationService.NotificationService.transformer.ThirdPartyDataTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

    @Autowired
    private BlacklistService blacklistService;

    @Autowired
    private ThirdPartyService thirdPartyService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private EsRepository esRepository;

    @Autowired
    private ThirdPartyDataTransformer thirdPartyDataTransformer;

    @Autowired
    private EsDataTransformer esDataTransformer;

    @Autowired
    private UpdateSmsFromThirdPartyResponse updateSmsFromThirdPartyResponse;

    @KafkaListener(topics = "myTopicc", groupId = "myGroup")
    public void consume(KafkaData kafkaData){

        Sms sms = notificationService.getSmsDetailsFromRequestId(kafkaData.getRequestId());

        //check blacklisted
        if(blacklistService.checkIfPhoneNumberIsBlacklisted(sms.getPhoneNumber())){
            log.info("phoneNumber is blacklisted");
            return;
        }

        ThirdPartyData thirdPartyData = thirdPartyDataTransformer.transform(sms);

        ThirdPartyResponse thirdPartyResponse = thirdPartyService.sendToThirdPartyProvider(thirdPartyData);

        if(thirdPartyResponse==null || !thirdPartyResponse.getResponse().getClass().getSimpleName().equals("ArrayList") || !thirdPartyResponse.getResponse().get(0).getCode().equals("1001")){
            log.info("thirdPartyData is not correct, therefore returned");
            return;
        }

        //updating sms
        sms = updateSmsFromThirdPartyResponse.update(sms, thirdPartyResponse);

        //saving into ES
        EsData esData = esDataTransformer.transform(sms);

        esRepository.save(esData);
    }
}
