package com.raf.hotelnotificationservice.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    private NotificationType type;
    private String email;
//    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy",timezone = "GMT+1")
//    private Instant instant;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate dateCreated;

    public Notification(String email, NotificationType type) {
        this.email = email;
        this.type = type;
        //this.instant = Instant.now();
        this.dateCreated = LocalDate.now();
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
