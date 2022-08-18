package com.example.notificationService.NotificationService.controller.notificationServiceController;

import com.example.notificationService.NotificationService.constant.SmsConstants;
import com.example.notificationService.NotificationService.entity.notificationService.Sms;
import com.example.notificationService.NotificationService.request.SmsRequest;
import com.example.notificationService.NotificationService.response.GenericResponse;
import com.example.notificationService.NotificationService.response.SmsResponse;
import com.example.notificationService.NotificationService.service.notificationService.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class NotificationServiceController {

    private final NotificationService notificationService;

    @PostMapping(value = SmsConstants.SEND_SMS_ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity sendSMS(@Valid @RequestBody SmsRequest smsRequest){

        log.info("SmsRequest received is: " + smsRequest);

        SmsResponse smsResponse = notificationService.sendSMS(smsRequest);

        GenericResponse<SmsResponse> response = new GenericResponse<>();
        response.setData(smsResponse);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = SmsConstants.GET_SMS_FROM_REQUEST_ID_ENDPINT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getSmsDetailsFromRequestId(@PathVariable("request_id") String requestId){

        log.info("request_id received is: " + requestId);

        Sms sms = notificationService.getSmsDetailsFromRequestId(requestId);

        GenericResponse<Sms> response = new GenericResponse<>();
        response.setData(sms);

        return ResponseEntity.ok().body(response);
    }
}
