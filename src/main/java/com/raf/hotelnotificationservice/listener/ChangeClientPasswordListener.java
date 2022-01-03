package com.raf.hotelnotificationservice.listener;

import com.raf.hotelnotificationservice.domain.*;
import com.raf.hotelnotificationservice.dto.ClientCreateDto;
import com.raf.hotelnotificationservice.dto.ClientPasswordDto;
import com.raf.hotelnotificationservice.repository.NotificationRepository;
import com.raf.hotelnotificationservice.repository.NotificationTypeRepository;
import com.raf.hotelnotificationservice.service.EmailService;
import javassist.NotFoundException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class ChangeClientPasswordListener {

    private MessageHelper messageHelper;
    private EmailService emailService;
    private NotificationRepository notificationRepository;
    private NotificationTypeRepository notificationTypeRepository;

    public ChangeClientPasswordListener(MessageHelper messageHelper, EmailService emailService, NotificationRepository notificationRepository, NotificationTypeRepository notificationTypeRepository) {
        this.messageHelper = messageHelper;
        this.emailService = emailService;
        this.notificationRepository = notificationRepository;
        this.notificationTypeRepository = notificationTypeRepository;
    }

    // TODO: mozda da se naprave 2 queuea ako nas ne bude mrzelo
    @JmsListener(destination = "${destination.passwordClient}", concurrency = "5-10")
    public void passwordClient(Message message) throws JMSException, NotFoundException {
        ClientPasswordDto clientPasswordDto = messageHelper.getMessage(message, ClientPasswordDto.class);

        String role = "client";

        NotificationType notificationType = notificationTypeRepository
                .findNotificationTypeByType(Type.PASSWORD_RESET)
                .orElseThrow(() -> new NotFoundException(String
                        .format("Notification of this type not found.")));

        Notification notification = new ChangePasswordNotification(clientPasswordDto.getEmail(), clientPasswordDto.getId(), role, notificationType);
        notificationRepository.save(notification);
        emailService.sendSimpleMessage(clientPasswordDto.getEmail(), "Password reset", notification);
    }

}
