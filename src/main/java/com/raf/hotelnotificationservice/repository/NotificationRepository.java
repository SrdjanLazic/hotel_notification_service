package com.raf.hotelnotificationservice.repository;

import com.raf.hotelnotificationservice.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {


}
