package com.raf.hotelnotificationservice.listener;

import com.raf.hotelnotificationservice.domain.Notification;
import com.raf.hotelnotificationservice.domain.VerifyEmailNotification;
import com.raf.hotelnotificationservice.dto.ClientCreateDto;
import com.raf.hotelnotificationservice.repository.NotificationRepository;
import com.raf.hotelnotificationservice.service.EmailService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class AddClientListener {

    private MessageHelper messageHelper;
    private EmailService emailService;
    private NotificationRepository notificationRepository;

    public AddClientListener(MessageHelper messageHelper, EmailService emailService, NotificationRepository notificationRepository) {
        this.messageHelper = messageHelper;
        this.emailService = emailService;
        this.notificationRepository = notificationRepository;
    }

    @JmsListener(destination = "${destination.addClient}", concurrency = "5-10")
    public void addClient(Message message) throws JMSException {
        ClientCreateDto clientCreateDto = messageHelper.getMessage(message, ClientCreateDto.class);
        Notification notification = new VerifyEmailNotification(clientCreateDto.getFirstName(), clientCreateDto.getLastName(), clientCreateDto.getEmail());
        notificationRepository.save(notification);
        emailService.sendSimpleMessage(clientCreateDto.getEmail(), "Email verification", notification);
    }
}
