package com.raf.hotelnotificationservice.mapper;

import com.raf.hotelnotificationservice.domain.Notification;
import com.raf.hotelnotificationservice.domain.NotificationType;
import com.raf.hotelnotificationservice.dto.NotificationDto;
import com.raf.hotelnotificationservice.dto.NotificationTypeDto;
import com.raf.hotelnotificationservice.repository.NotificationRepository;
import com.raf.hotelnotificationservice.repository.NotificationTypeRepository;
import org.springframework.stereotype.Component;

@Component
public class NotificationTypeMapper {

    private NotificationTypeRepository notificationTypeRepository;

    public NotificationTypeMapper(NotificationTypeRepository notificationTypeRepository) {
        this.notificationTypeRepository = notificationTypeRepository;
    }

    public NotificationTypeDto notificationTypeToNotificationTypeDto(NotificationType notificationType) {
        NotificationTypeDto notificationTypeDto = new NotificationTypeDto();
        notificationTypeDto.setType(notificationType.getType());
        notificationTypeDto.setMessage(notificationType.getMessage());
        notificationTypeDto.setId(notificationType.getId());
        return notificationTypeDto;
    }


}
