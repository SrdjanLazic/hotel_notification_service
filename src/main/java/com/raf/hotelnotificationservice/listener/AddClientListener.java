package com.raf.hotelnotificationservice.listener;

import com.raf.hotelnotificationservice.domain.Notification;
import com.raf.hotelnotificationservice.domain.NotificationType;
import com.raf.hotelnotificationservice.domain.Type;
import com.raf.hotelnotificationservice.domain.VerifyEmailNotification;
import com.raf.hotelnotificationservice.dto.ClientCreateDto;
import com.raf.hotelnotificationservice.repository.NotificationRepository;
import com.raf.hotelnotificationservice.repository.NotificationTypeRepository;
import com.raf.hotelnotificationservice.service.EmailService;
import javassist.NotFoundException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class AddClientListener {

    private MessageHelper messageHelper;
    private EmailService emailService;
    private NotificationRepository notificationRepository;
    private NotificationTypeRepository notificationTypeRepository;

    public AddClientListener(MessageHelper messageHelper, EmailService emailService, NotificationRepository notificationRepository, NotificationTypeRepository notificationTypeRepository) {
        this.messageHelper = messageHelper;
        this.emailService = emailService;
        this.notificationRepository = notificationRepository;
        this.notificationTypeRepository = notificationTypeRepository;
    }

    @JmsListener(destination = "${destination.addClient}", concurrency = "5-10")
    public void addClient(Message message) throws JMSException, NotFoundException {
        ClientCreateDto clientCreateDto = messageHelper.getMessage(message, ClientCreateDto.class);
        String role = "client";

        NotificationType notificationType = notificationTypeRepository
                .findNotificationTypeByType(Type.EMAIL_VERIFICATION)
                .orElseThrow(() -> new NotFoundException(String
                        .format("Notification of this type not found.")));

        Notification notification = new VerifyEmailNotification(clientCreateDto.getFirstName(), clientCreateDto.getLastName(), clientCreateDto.getEmail(), role, notificationType);
        notificationRepository.save(notification);
        emailService.sendSimpleMessage(clientCreateDto.getEmail(), "Email verification", notification);
    }
}
