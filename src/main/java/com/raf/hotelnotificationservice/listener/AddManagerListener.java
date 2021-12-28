package com.raf.hotelnotificationservice.listener;

import com.raf.hotelnotificationservice.domain.Notification;
import com.raf.hotelnotificationservice.domain.VerifyEmailNotification;
import com.raf.hotelnotificationservice.dto.ManagerCreateDto;
import com.raf.hotelnotificationservice.repository.NotificationRepository;
import com.raf.hotelnotificationservice.service.EmailService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class AddManagerListener {

    private MessageHelper messageHelper;
    private EmailService emailService;
    private NotificationRepository notificationRepository;

    public AddManagerListener(MessageHelper messageHelper, EmailService emailService, NotificationRepository notificationRepository) {
        this.messageHelper = messageHelper;
        this.emailService = emailService;
        this.notificationRepository = notificationRepository;
    }

    @JmsListener(destination = "${destination.addManager}", concurrency = "5-10")
    public void addManager(Message message) throws JMSException {
        ManagerCreateDto managerCreateDto = messageHelper.getMessage(message, ManagerCreateDto.class);
        String role = "manager";
        Notification notification = new VerifyEmailNotification(managerCreateDto.getFirstName(), managerCreateDto.getLastName(), managerCreateDto.getEmail(), role);
        notificationRepository.save(notification);
        emailService.sendSimpleMessage(managerCreateDto.getEmail(), "Email verification", notification);
    }
}