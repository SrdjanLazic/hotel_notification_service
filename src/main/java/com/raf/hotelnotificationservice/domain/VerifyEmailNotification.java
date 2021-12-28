package com.raf.hotelnotificationservice.domain;

import javax.persistence.*;

@Entity
public class VerifyEmailNotification extends Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;

    public VerifyEmailNotification(String name, String lastName, String email) {
        super(name, lastName, email);
        this.message = String.format("Hi, %s %s. To complete your registration, please click the following link: http://localhost:8080/api/client/verifyMail/%s.", name, lastName, email);
    }

    public VerifyEmailNotification() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }
}
