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
public class ImiData {

    @JsonProperty("deliverychannel")
    private String deliveryChannel;

    @JsonProperty("channels")
    private Channels channels;

    @JsonProperty("destination")
    private List<DestinationData> destination;
}
