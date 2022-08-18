package com.example.notificationService.NotificationService.controller.blacklistController;

import com.example.notificationService.NotificationService.constant.BlacklistConstants;
import com.example.notificationService.NotificationService.constant.ResponseConstants;
import com.example.notificationService.NotificationService.entity.redis.PhoneNumbers;
import com.example.notificationService.NotificationService.response.GenericResponse;
import com.example.notificationService.NotificationService.service.blacklistService.BlacklistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class BlacklistController {

    private final BlacklistService blacklistService;

    @PostMapping(value = BlacklistConstants.ADD_TO_BLACKLIST_ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addToBlacklist(@RequestBody PhoneNumbers phoneNumbers){

        log.info("phoneNumbers received to add to blacklist are: " + phoneNumbers);

        phoneNumbers.getPhoneNumbers().forEach(phoneNumber->{
            blacklistService.addToBlacklist(phoneNumber);
        });

        GenericResponse<String> response = new GenericResponse<>();
        response.setData(ResponseConstants.SUCCESSFULLY_BLACKLISTED);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value = BlacklistConstants.REMOVE_FROM_BLACKLIST_ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity removeFromBlacklist(@RequestBody PhoneNumbers phoneNumbers){

        log.info("phoneNumbers received to remove from blacklist are: " + phoneNumbers);

        phoneNumbers.getPhoneNumbers().forEach(phoneNumber->{
            blacklistService.removeFromBlacklist(phoneNumber);
        });

        GenericResponse<String> response = new GenericResponse<>();
        response.setData(ResponseConstants.SUCCESSFULLY_WHITELISTED);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = BlacklistConstants.GET_ALL_BLACKLISTED_NUMBERS_ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllBlacklistedNumbers(){

        GenericResponse<List<String>> response = new GenericResponse<>();
        response.setData(blacklistService.getAllBlacklistedNumbers());

        return ResponseEntity.ok().body(response);
    }
}
