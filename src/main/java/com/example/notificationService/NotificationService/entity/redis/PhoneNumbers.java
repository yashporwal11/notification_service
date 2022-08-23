package com.example.notificationService.NotificationService.entity.redis;

import com.example.notificationService.NotificationService.constant.BlacklistConstants;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(BlacklistConstants.REDIS_HASH_KEY)
public class PhoneNumbers implements Serializable {

    @JsonProperty("phoneNumbers")
    private List<String> phoneNumbers;
}
