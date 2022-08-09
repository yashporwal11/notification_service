package com.example.notificationService.NotificationService.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SmsResponse {

    @JsonProperty("request_id")
    private String requestId;

    @JsonProperty("comments")
    private String comments;

}
