package com.example.notificationService.NotificationService.entity.notificationService;

import com.example.notificationService.NotificationService.entity.enums.StatusType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "sms_requests")
public class Sms {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "request_id", unique=true,updatable = false, nullable = false)
    private String requestId;

    @PrePersist
    public void autofill() {
        this.requestId=UUID.randomUUID().toString();
    }

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "message")
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusType status;

    @Column(name = "failure_code")
    private String failureCode;

    @Column(name = "failure_comments")
    private String failureComments;

    @Column(name = "created_at")
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date updatedAt;
}






