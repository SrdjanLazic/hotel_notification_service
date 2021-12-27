package com.raf.hotelnotificationservice.listener;

import com.raf.hotelnotificationservice.dto.ClientCreateDto;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class AddClientListener {

    private MessageHelper messageHelper;

    public AddClientListener(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }

    // metoda koja konstantno osluskuje Queue za nove poruke
    // ${destination.createOrder} - Queue sa kog citamo poruku (isti onaj na koji smo poslali poruku u ProductController-u)
    // concurrency - 5 do 10 thread-ova
    // message je u JSON formatu, poziva se getMessage koji radi deserijalizaciju JSON poruke u objekat
    @JmsListener(destination = "${destination.addClient}", concurrency = "5-10")
    public void addClient(Message message) throws JMSException {
        System.out.println("Primljena poruka iz Queue-a");
        //ClientCreateDto clientCreateDto = messageHelper.getMessage(message, ClientCreateDto.class);
        // nakon sto je getMessage konvertovao JSON u objekat, dodajemo porudzbinu
    }
}
