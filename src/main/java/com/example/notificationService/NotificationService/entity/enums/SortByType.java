package com.example.notificationService.NotificationService.entity.enums;

public enum SortByType {

    ID("id"),
    PHONE_NUMBER("phoneNumber"),
    MESSAGE("message.raw"),
    CREATED_AT("createdAt");
    public String value;

    SortByType (String value){
        this.value = value;
    }

}
