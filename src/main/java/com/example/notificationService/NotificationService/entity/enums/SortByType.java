package com.example.notificationService.NotificationService.entity.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum SortByType {

    @JsonProperty("id")
    ID("id"),

    @JsonProperty("phone_number")
    PHONE_NUMBER("phoneNumber"),

    @JsonProperty("message")
    MESSAGE("message.raw"), // ".raw" to make inner FieldType Keyword for sorting purposes

    @JsonProperty("created_at")
    CREATED_AT("createdAt");

    public String value;

    SortByType (String value){
        this.value = value;
    }
}
