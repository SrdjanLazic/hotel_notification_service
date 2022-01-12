package com.raf.hotelnotificationservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.raf.hotelnotificationservice.domain.NotificationType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;
import java.time.LocalDate;

public class NotificationDto {

    private Long id;
    private String message;
    private String email;
    private NotificationType type;
//    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy",timezone = "GMT+1")
//    private Instant instant;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate dateCreated;

    public NotificationDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

//    public Instant getInstant() {
//        return instant;
//    }
//
//    public void setInstant(Instant instant) {
//        this.instant = instant;
//    }


    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }
}
