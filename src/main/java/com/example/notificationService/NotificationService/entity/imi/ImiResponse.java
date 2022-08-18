package com.example.notificationService.NotificationService.entity.imi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImiResponse {

    @JsonProperty("response")
    private List<ImiResponseData> response;
}
