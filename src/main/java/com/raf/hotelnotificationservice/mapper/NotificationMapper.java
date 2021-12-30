package com.raf.hotelnotificationservice.mapper;

import com.raf.hotelnotificationservice.domain.Notification;
import com.raf.hotelnotificationservice.dto.NotificationDto;
import com.raf.hotelnotificationservice.repository.NotificationRepository;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    private NotificationRepository notificationRepository;

    public NotificationMapper(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public NotificationDto notificationToNotificationDto(Notification notification) {
        System.out.println("Usao u mapper");
        System.out.println(notification);
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setEmail(notification.getEmail());
        notificationDto.setId(notification.getId());
        notificationDto.setMessage(notification.getMessage());
        notificationDto.setType(notification.getType());
        System.out.println(notificationDto);
        return notificationDto;
    }
}
