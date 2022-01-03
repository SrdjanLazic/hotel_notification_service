package com.raf.hotelnotificationservice.listener;

import com.raf.hotelnotificationservice.domain.Notification;
import com.raf.hotelnotificationservice.domain.NotificationType;
import com.raf.hotelnotificationservice.domain.Type;
import com.raf.hotelnotificationservice.domain.VerifyEmailNotification;
import com.raf.hotelnotificationservice.dto.ManagerCreateDto;
import com.raf.hotelnotificationservice.repository.NotificationRepository;
import com.raf.hotelnotificationservice.repository.NotificationTypeRepository;
import com.raf.hotelnotificationservice.service.EmailService;
import javassist.NotFoundException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class AddManagerListener {

    private MessageHelper messageHelper;
    private EmailService emailService;
    private NotificationRepository notificationRepository;
    private NotificationTypeRepository notificationTypeRepository;

    public AddManagerListener(MessageHelper messageHelper, EmailService emailService, NotificationRepository notificationRepository,
                              NotificationTypeRepository notificationTypeRepository) {
        this.messageHelper = messageHelper;
        this.emailService = emailService;
        this.notificationRepository = notificationRepository;
        this.notificationTypeRepository = notificationTypeRepository;
    }

    @JmsListener(destination = "${destination.addManager}", concurrency = "5-10")
    public void addManager(Message message) throws JMSException, NotFoundException {
        ManagerCreateDto managerCreateDto = messageHelper.getMessage(message, ManagerCreateDto.class);
        String role = "manager";

        NotificationType notificationType = notificationTypeRepository
                .findNotificationTypeByType(Type.EMAIL_VERIFICATION)
                .orElseThrow(() -> new NotFoundException(String
                        .format("Notification of this type not found.")));

        Notification notification = new VerifyEmailNotification(managerCreateDto.getFirstName(), managerCreateDto.getLastName(), managerCreateDto.getEmail(), role, notificationType);
        notificationRepository.save(notification);
        emailService.sendSimpleMessage(managerCreateDto.getEmail(), "Email verification", notification);
    }
}