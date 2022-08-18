package com.example.notificationService.NotificationService.entity.imi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImiResponseData {

    @JsonProperty("code")
    private String code;

    @JsonProperty("transid")
    private String transId;

    @JsonProperty("description")
    private String description;

    @JsonProperty("correlationid")
    private String correlationId;
}
