package com.raf.hotelnotificationservice.domain;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    private NotificationType type;
    private String email;

    public Notification(String email, NotificationType type) {
        this.email = email;
        this.type = type;
    }

    public Notification() {
    }

    public String getMessage() {
        return getType().getMessage();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }
}
