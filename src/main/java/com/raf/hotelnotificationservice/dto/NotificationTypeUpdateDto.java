package com.raf.hotelnotificationservice.dto;

import javax.validation.constraints.NotBlank;

public class NotificationTypeUpdateDto {

    @NotBlank
    private String message;

    public NotificationTypeUpdateDto() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
