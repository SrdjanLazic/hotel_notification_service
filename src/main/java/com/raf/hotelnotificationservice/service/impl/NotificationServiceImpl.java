package com.raf.hotelnotificationservice.service.impl;

import com.raf.hotelnotificationservice.dto.NotificationDto;
import com.raf.hotelnotificationservice.mapper.NotificationMapper;
import com.raf.hotelnotificationservice.repository.NotificationRepository;
import com.raf.hotelnotificationservice.service.NotificationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    private NotificationRepository notificationRepository;
    private NotificationMapper notificationMapper;

    public NotificationServiceImpl(NotificationRepository notificationRepository, NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
    }

    @Override
    public Page<NotificationDto> findAll(Pageable pageable) {
        System.out.println("Usao u find all metodu");
        return notificationRepository.findAll(pageable)
                .map(notificationMapper::notificationToNotificationDto);
    }
}