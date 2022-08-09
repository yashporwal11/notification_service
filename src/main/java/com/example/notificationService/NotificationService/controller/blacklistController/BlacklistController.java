package com.example.notificationService.NotificationService.controller.blacklistController;

import com.example.notificationService.NotificationService.constant.BlacklistConstants;
import com.example.notificationService.NotificationService.entity.redis.PhoneNumbers;
import com.example.notificationService.NotificationService.response.GenericResponse;
import com.example.notificationService.NotificationService.service.blacklistService.BlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlacklistController {

    @Autowired
    private BlacklistService blacklistService;

    @PostMapping(BlacklistConstants.ADD_TO_BLACKLIST_ENDPOINT)
    public ResponseEntity addToBlacklist(@RequestBody PhoneNumbers phoneNumbers){

        phoneNumbers.getPhoneNumbers().forEach(phoneNumber->{
            blacklistService.addToBlacklist(phoneNumber);
        });

        GenericResponse<String> response = new GenericResponse<>();
        response.setData("Successfully blacklisted");

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(BlacklistConstants.REMOVE_FROM_BLACKLIST_ENDPOINT)
    public ResponseEntity removeFromBlacklist(@RequestBody PhoneNumbers phoneNumbers){

        phoneNumbers.getPhoneNumbers().forEach(phoneNumber->{
            blacklistService.removeFromBlacklist(phoneNumber);
        });

        GenericResponse<String> response = new GenericResponse<>();
        response.setData("Successfully whitelisted");

        return ResponseEntity.ok().body(response);
    }

    @GetMapping(BlacklistConstants.GET_ALL_BLACKLISTED_NUMBERS_ENDPOINT)
    public ResponseEntity getAllBlacklistedNumbers(){

        GenericResponse<List<String>> response = new GenericResponse<>();
        response.setData(blacklistService.getAllBlacklistedNumbers());

        return ResponseEntity.ok().body(response);
    }

}
