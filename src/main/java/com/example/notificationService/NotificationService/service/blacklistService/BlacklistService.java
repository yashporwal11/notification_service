package com.example.notificationService.NotificationService.service.blacklistService;


import java.util.List;

public interface BlacklistService {
    void addToBlacklist(String phoneNumber);

    List<String> getAllBlacklistedNumbers();

    boolean checkIfPhoneNumberIsBlacklisted(String phoneNumber);

    void removeFromBlacklist(String phoneNumber);
}
