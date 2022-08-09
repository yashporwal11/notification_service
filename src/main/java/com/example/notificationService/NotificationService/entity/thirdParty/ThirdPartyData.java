package com.example.notificationService.NotificationService.entity.thirdParty;

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
public class ThirdPartyData {

    @JsonProperty("deliverychannel")
    private String deliveryChannel;

    private Channels channels;

    private List<DestinationData> destination;
}
