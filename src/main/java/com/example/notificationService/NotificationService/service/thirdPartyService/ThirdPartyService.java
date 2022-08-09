package com.example.notificationService.NotificationService.service.thirdPartyService;

import com.example.notificationService.NotificationService.entity.thirdParty.ThirdPartyData;
import com.example.notificationService.NotificationService.entity.thirdParty.ThirdPartyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ThirdPartyService {

    @Autowired
    private RestTemplate restTemplate;

    public ThirdPartyResponse sendToThirdPartyProvider(ThirdPartyData thirdPartyData) {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("key","93ceffda-5941-11ea-9da9-025282c394f2");

        HttpEntity<Object> requestEntity = new HttpEntity<>(thirdPartyData, headers);

        try {
            ResponseEntity<ThirdPartyResponse> responseEntity = restTemplate.exchange("https://api.imiconnect.in/resources/v1/messaging",
                    HttpMethod.POST,
                    requestEntity,
                    ThirdPartyResponse.class);

            return responseEntity.getBody();
        }
        catch (Exception ex){
            log.error("error while calling thirdPartyApi : " + ex.getMessage());
            return null;
        }
    }
}
