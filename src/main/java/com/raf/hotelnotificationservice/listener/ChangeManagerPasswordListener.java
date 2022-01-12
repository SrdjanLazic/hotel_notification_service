package com.raf.hotelnotificationservice.listener;

import com.raf.hotelnotificationservice.domain.ChangePasswordNotification;
import com.raf.hotelnotificationservice.domain.Notification;
import com.raf.hotelnotificationservice.domain.NotificationType;
import com.raf.hotelnotificationservice.domain.Type;
import com.raf.hotelnotificationservice.dto.ClientPasswordDto;
import com.raf.hotelnotificationservice.dto.ManagerPasswordDto;
import com.raf.hotelnotificationservice.repository.NotificationRepository;
import com.raf.hotelnotificationservice.repository.NotificationTypeRepository;
import com.raf.hotelnotificationservice.service.EmailService;
import javassist.NotFoundException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class ChangeManagerPasswordListener {

    private MessageHelper messageHelper;
    private EmailService emailService;
    private NotificationRepository notificationRepository;
    private NotificationTypeRepository notificationTypeRepository;

    public ChangeManagerPasswordListener(MessageHelper messageHelper, EmailService emailService, NotificationRepository notificationRepository, NotificationTypeRepository notificationTypeRepository) {
        this.messageHelper = messageHelper;
        this.emailService = emailService;
        this.notificationRepository = notificationRepository;
        this.notificationTypeRepository = notificationTypeRepository;
    }

    // TODO: mozda da se naprave 2 queuea ako nas ne bude mrzelo
    @JmsListener(destination = "${destination.passwordManager}", concurrency = "5-10")
    public void passwordManager(Message message) throws JMSException, NotFoundException {
        ManagerPasswordDto managerPasswordDto = messageHelper.getMessage(message, ManagerPasswordDto.class);

        String role = "manager";

        NotificationType notificationType = notificationTypeRepository
                .findNotificationTypeByType(Type.PASSWORD_RESET)
                .orElseThrow(() -> new NotFoundException(String
                        .format("Notification of this type not found.")));

        Notification notification = new ChangePasswordNotification(managerPasswordDto.getEmail(), managerPasswordDto.getId(), role, notificationType);
        notificationRepository.save(notification);
        emailService.sendSimpleMessage(managerPasswordDto.getEmail(), "Password reset", notification);
    }
}
