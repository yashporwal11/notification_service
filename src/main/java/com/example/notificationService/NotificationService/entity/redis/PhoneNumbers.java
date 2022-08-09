package com.example.notificationService.NotificationService.entity.redis;

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
@RedisHash("PhoneNumber")
public class PhoneNumbers implements Serializable {
    private List<String> phoneNumbers;
}
