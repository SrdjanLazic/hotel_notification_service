package com.raf.hotelnotificationservice.repository;

import com.raf.hotelnotificationservice.domain.Notification;
import com.raf.hotelnotificationservice.domain.NotificationType;
import com.raf.hotelnotificationservice.domain.Type;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    //Koristimo ga za arhivu posto nam treba iskljucivo save
    List<Notification> findNotificationByEmail(String email);

}

