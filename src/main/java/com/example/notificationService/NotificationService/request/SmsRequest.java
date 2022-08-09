package com.example.notificationService.NotificationService.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SmsRequest {

    @NotBlank(message = "phone_number is mandatory")
    @Pattern(regexp = "^((\\+){1}91){1}[1-9]{1}[0-9]{9}$", message = "phone_number should start with +91 and should have 10 more digits")
    private String phoneNumber;

    private String message;
}
