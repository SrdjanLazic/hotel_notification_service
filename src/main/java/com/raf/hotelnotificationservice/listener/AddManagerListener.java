package com.raf.hotelnotificationservice.listener;

import com.raf.hotelnotificationservice.dto.ManagerCreateDto;
import com.raf.hotelnotificationservice.service.EmailService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class AddManagerListener {

    private MessageHelper messageHelper;
    private EmailService emailService;

    public AddManagerListener(MessageHelper messageHelper, EmailService emailService) {
        this.messageHelper = messageHelper;
        this.emailService = emailService;
    }

    @JmsListener(destination = "${destination.addManager}", concurrency = "5-10")
    public void addManager(Message message) throws JMSException {
        ManagerCreateDto managerCreateDto = messageHelper.getMessage(message, ManagerCreateDto.class);
    }
}