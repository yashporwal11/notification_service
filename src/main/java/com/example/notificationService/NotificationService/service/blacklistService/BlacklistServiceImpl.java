package com.example.notificationService.NotificationService.service.blacklistService;

import com.example.notificationService.NotificationService.repository.BlacklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlacklistServiceImpl implements BlacklistService{

    @Autowired
    private BlacklistRepository blacklistRepository;

    @Override
    public void addToBlacklist(String phoneNumber) {
        blacklistRepository.addToBlacklist(phoneNumber);
    }

    @Override
    public List<String> getAllBlacklistedNumbers() {
        return blacklistRepository.getAllBlacklistedNumbers();
    }

    @Override
    public boolean checkIfPhoneNumberIsBlacklisted(String phoneNumber) {
        return blacklistRepository.checkIfPhoneNumberIsBlacklisted(phoneNumber);
    }

    @Override
    public void removeFromBlacklist(String phoneNumber) {
        blacklistRepository.removeFromBlacklist(phoneNumber);
    }
}
