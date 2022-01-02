package com.raf.hotelnotificationservice.service;

import com.raf.hotelnotificationservice.dto.NotificationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface NotificationService {

    Page<NotificationDto> findAll(Pageable pageable);

    Page<NotificationDto> findClientNotifications(Long id, Pageable pageable);

    Page<NotificationDto> findManagerNotifications(Long id, Pageable pageable);


}
