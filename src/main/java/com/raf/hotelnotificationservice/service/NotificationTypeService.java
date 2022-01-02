package com.raf.hotelnotificationservice.service;

import com.raf.hotelnotificationservice.dto.NotificationTypeDto;
import com.raf.hotelnotificationservice.dto.NotificationTypeUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotificationTypeService {

    Page<NotificationTypeDto> findAll(Pageable pageable);

    NotificationTypeDto updateNotificationType(Long id, NotificationTypeUpdateDto notificationTypeUpdateDto);

    void deleteNotificationType(Long id);
}
