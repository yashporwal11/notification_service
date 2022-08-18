package com.example.notificationService.NotificationService.entity.imi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Channels {

    @JsonProperty("sms")
    private SmsImi smsImi;
}
