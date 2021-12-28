package com.raf.hotelnotificationservice.listener;

import com.raf.hotelnotificationservice.dto.ClientCreateDto;
import com.raf.hotelnotificationservice.service.EmailService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class AddClientListener {

    private MessageHelper messageHelper;
    private EmailService emailService;

    public AddClientListener(MessageHelper messageHelper, EmailService emailService) {
        this.messageHelper = messageHelper;
        this.emailService = emailService;
    }

    @JmsListener(destination = "${destination.addClient}", concurrency = "5-10")
    public void addClient(Message message) throws JMSException {
        ClientCreateDto clientCreateDto = messageHelper.getMessage(message, ClientCreateDto.class);
        //emailService.sendSimpleMessage("srdjanlazic1996@gmail.com", "Radi!", "Najs, radi ovo!");
    }
}
