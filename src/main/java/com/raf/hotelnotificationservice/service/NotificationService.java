package com.raf.hotelnotificationservice.service;

import com.raf.hotelnotificationservice.dto.NotificationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotificationService {

    Page<NotificationDto> findAll(Pageable pageable);

    Page<NotificationDto> findClientNotifications(Long id, Pageable pageable);

    Page<NotificationDto> findManagerNotifications(Long id, Pageable pageable);

    Page<NotificationDto> findByEmail(String email, Pageable pageable);

    Page<NotificationDto> findByType(String type, Pageable pageable);

    Page<NotificationDto> findBetweenDates(String date1, String date2, Pageable pageable);

}
