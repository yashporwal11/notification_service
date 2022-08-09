package com.example.notificationService.NotificationService.controller.notificationServiceController;

import com.example.notificationService.NotificationService.constant.SmsConstants;
import com.example.notificationService.NotificationService.entity.notificationService.Sms;
import com.example.notificationService.NotificationService.request.SmsRequest;
import com.example.notificationService.NotificationService.response.GenericResponse;
import com.example.notificationService.NotificationService.response.SmsResponse;
import com.example.notificationService.NotificationService.service.notificationService.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class NotificationServiceController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping(SmsConstants.SEND_SMS_ENDPOINT)
    public ResponseEntity sendSMS(@Valid @RequestBody SmsRequest smsRequest){

        Sms sms = notificationService.sendSMS(smsRequest);

        GenericResponse<SmsResponse> response = new GenericResponse<>();
        SmsResponse data = new SmsResponse();
        data.setRequestId(sms.getRequestId());
        data.setComments("Successfully Sent");
        response.setData(data);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping(SmsConstants.GET_SMS_FROM_REQUEST_ID_ENDPINT)
    public ResponseEntity getSmsDetailsFromRequestId(@PathVariable("request_id") String requestId){
        Sms sms = notificationService.getSmsDetailsFromRequestId(requestId);

        GenericResponse<Sms> response = new GenericResponse<>();
        response.setData(sms);

        return ResponseEntity.ok().body(response);
    }

}
