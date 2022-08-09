package com.example.notificationService.NotificationService.repository;

import com.example.notificationService.NotificationService.entity.notificationService.Sms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationServiceRepository extends JpaRepository<Sms, Integer> {

    Sms findByRequestId(String requestId);
}
