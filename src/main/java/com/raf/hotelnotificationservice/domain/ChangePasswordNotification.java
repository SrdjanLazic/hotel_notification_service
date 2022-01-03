package com.raf.hotelnotificationservice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ChangePasswordNotification extends Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;

    public ChangePasswordNotification(String email, Long id,  String role, NotificationType type) {
        super(email, type);
        this.message = String.format(super.getMessage(), role, id);
    }

    public ChangePasswordNotification() {
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
