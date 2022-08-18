package com.example.notificationService.NotificationService.config;

import com.example.notificationService.NotificationService.constant.RestTemplateConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate customRestTemplate()
    {
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectTimeout(RestTemplateConstants.CONNECT_TIMEOUT);
        httpRequestFactory.setReadTimeout(RestTemplateConstants.READ_TIMEOUT);
        return new RestTemplate(httpRequestFactory);
    }
}