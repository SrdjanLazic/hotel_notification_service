package com.raf.hotelnotificationservice.service.impl;

import com.raf.hotelnotificationservice.domain.NotificationType;
import com.raf.hotelnotificationservice.dto.NotificationDto;
import com.raf.hotelnotificationservice.dto.NotificationTypeDto;
import com.raf.hotelnotificationservice.dto.NotificationTypeUpdateDto;
import com.raf.hotelnotificationservice.exception.NotFoundException;
import com.raf.hotelnotificationservice.mapper.NotificationMapper;
import com.raf.hotelnotificationservice.mapper.NotificationTypeMapper;
import com.raf.hotelnotificationservice.repository.NotificationRepository;
import com.raf.hotelnotificationservice.repository.NotificationTypeRepository;
import com.raf.hotelnotificationservice.service.NotificationTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NotificationTypeServiceImpl implements NotificationTypeService {

    private NotificationTypeRepository notificationTypeRepository;
    private NotificationTypeMapper notificationTypeMapper;

    public NotificationTypeServiceImpl(NotificationTypeRepository notificationTypeRepository, NotificationTypeMapper notificationTypeMapper) {
        this.notificationTypeRepository = notificationTypeRepository;
        this.notificationTypeMapper = notificationTypeMapper;
    }

    @Override
    public Page<NotificationTypeDto> findAll(Pageable pageable) {
        return notificationTypeRepository.findAll(pageable)
                .map(notificationTypeMapper::notificationTypeToNotificationTypeDto);
    }

    @Override
    public NotificationTypeDto updateNotificationType(Long id, NotificationTypeUpdateDto notificationTypeUpdateDto) {
        NotificationType notificationType = notificationTypeRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Notification type with id: %d not found.", id)));


        notificationType.setMessage(notificationTypeUpdateDto.getMessage());
        return notificationTypeMapper.notificationTypeToNotificationTypeDto(notificationTypeRepository.save(notificationType));
    }

    @Override
    public void deleteNotificationType(Long id) {
        NotificationType notificationType = notificationTypeRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Notification type with id: %d not found.", id)));

        notificationTypeRepository.delete(notificationType);
    }


}
