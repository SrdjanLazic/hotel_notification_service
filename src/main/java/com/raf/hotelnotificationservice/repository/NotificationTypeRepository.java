package com.raf.hotelnotificationservice.repository;

import com.raf.hotelnotificationservice.domain.NotificationType;
import com.raf.hotelnotificationservice.domain.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationTypeRepository extends JpaRepository<NotificationType, Long> {

    Optional<NotificationType> findNotificationTypeByType(Type type);

}
