package com.raf.hotelnotificationservice.repository;

import com.raf.hotelnotificationservice.domain.Notification;
import com.raf.hotelnotificationservice.domain.NotificationType;
import com.raf.hotelnotificationservice.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    //Koristimo ga za arhivu posto nam treba iskljucivo save
    Optional<Notification> findNotificationByEmail(String email);

}

