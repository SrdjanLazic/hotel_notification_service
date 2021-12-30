package com.raf.hotelnotificationservice.domain;

import org.springframework.data.annotation.ReadOnlyProperty;

import javax.persistence.*;

@Entity
public class VerifyEmailNotification extends Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;

    public VerifyEmailNotification(String name, String lastName, String email, String role, NotificationType type) {
        super(email, type);
        this.message = String.format(super.getMessage(), name, lastName, role, email);
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


}
