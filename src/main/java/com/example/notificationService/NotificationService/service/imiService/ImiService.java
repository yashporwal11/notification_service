package com.example.notificationService.NotificationService.service.imiService;

import com.example.notificationService.NotificationService.constant.ImiConstants;
import com.example.notificationService.NotificationService.entity.imi.ImiData;
import com.example.notificationService.NotificationService.entity.imi.ImiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ImiService {

    private final RestTemplate restTemplate;

    @Value("${imi.key}")
    private String imiKey;

    public ImiResponse sendToImi(ImiData imiData) {

        log.info("imiData received is: " + imiData);

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("key", imiKey);

        HttpEntity<ImiData> requestEntity = new HttpEntity<>(imiData, headers);

        try {
            ResponseEntity<ImiResponse> responseEntity = restTemplate.exchange(ImiConstants.URL,
                    HttpMethod.POST,
                    requestEntity,
                    ImiResponse.class);

            return responseEntity.getBody();
        } catch (Exception ex) {
            log.error("error while calling imi : " + ex.getMessage());
            return null;
        }
    }
}
