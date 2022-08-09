package com.example.notificationService.NotificationService.entity.thirdParty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThirdPartyResponse {
    private List<ThirdPartyResponseData> response;
}
