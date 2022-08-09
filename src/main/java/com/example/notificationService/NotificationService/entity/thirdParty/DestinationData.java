package com.example.notificationService.NotificationService.entity.thirdParty;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DestinationData {

    private List<String> msisdn;

    @JsonProperty("correlationid")
    private String correlationId;
}
