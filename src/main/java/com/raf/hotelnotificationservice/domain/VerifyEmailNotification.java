package com.raf.hotelnotificationservice.domain;

import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;

@Entity
public class VerifyEmailNotification extends Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private String type;

    public VerifyEmailNotification(String name, String lastName, String email, String role) {
        super(email, role);
        this.type = "EmailVerification";
        this.message = String.format("Hi, %s %s. To complete your registration, please click the following link: http://localhost:8080/api/%s/verifyMail/%s.", name, lastName, role, email);
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
