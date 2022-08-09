package com.example.notificationService.NotificationService.entity.thirdParty;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThirdPartyResponseData {

    private String code;

    @JsonProperty("transid")
    private String transId;

    private String description;

    @JsonProperty("correlationid")
    private String correlationId;
}
