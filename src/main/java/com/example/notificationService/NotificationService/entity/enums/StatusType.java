package com.example.notificationService.NotificationService.entity.enums;

public enum StatusType {

    IN_PROGRESS("in_progress"),
    PROCESSED("processed"),
    BLACKLISTED("blacklisted");

    private final String value;

    StatusType(String value){
        this.value = value;
    }
}
