package com.raf.hotelnotificationservice.service;

import com.raf.hotelnotificationservice.dto.NotificationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotificationService {

    Page<NotificationDto> findAll(Pageable pageable);

}
