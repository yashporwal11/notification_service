package com.example.notificationService.NotificationService.repository;

import com.example.notificationService.NotificationService.constant.BlacklistConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BlacklistRepository {

    public static final String HASH_KEY = BlacklistConstants.REDIS_HASH_KEY;

    private final RedisTemplate template;

    public void addToBlacklist(String phoneNumber){
        template.opsForHash().put(HASH_KEY,phoneNumber,phoneNumber);
    }

    public List<String> getAllBlacklistedNumbers(){
        return template.opsForHash().values(HASH_KEY);
    }

    public boolean checkIfPhoneNumberIsBlacklisted(String phoneNumber){
        return !(template.opsForHash().get(HASH_KEY,phoneNumber)==null);
    }

    public void removeFromBlacklist(String phoneNumber){
        template.opsForHash().delete(HASH_KEY,phoneNumber);
    }
}
