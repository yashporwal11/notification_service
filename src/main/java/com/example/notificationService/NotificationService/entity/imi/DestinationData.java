package com.example.notificationService.NotificationService.entity.imi;

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

    @JsonProperty("msisdn")
    private List<String> msisdn;

    @JsonProperty("correlationid")
    private String correlationId;
}
