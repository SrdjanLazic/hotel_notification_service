package com.raf.hotelnotificationservice.controller;

import com.raf.hotelnotificationservice.dto.NotificationDto;
import com.raf.hotelnotificationservice.security.CheckSecurity;
import com.raf.hotelnotificationservice.service.NotificationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Page<NotificationDto>> getAllNotifications(@RequestHeader("Authorization") String authorization,
                                                               Pageable pageable) {
        return new ResponseEntity<>(notificationService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/client/{id}")
    @CheckSecurity(roles = {"ROLE_CLIENT"})
    public ResponseEntity<Page<NotificationDto>> getClientNotifications(@RequestHeader("Authorization") String authorization,
                                                                    @PathVariable("id") Long id, Pageable pageable) {
        return new ResponseEntity<>(notificationService.findClientNotifications(id, pageable), HttpStatus.OK);
    }

    @GetMapping("/manager/{id}")
    @CheckSecurity(roles = {"ROLE_MANAGER"})
    public ResponseEntity<Page<NotificationDto>> getManagerNotifications(@RequestHeader("Authorization") String authorization,
                                                                    @PathVariable("id") Long id, Pageable pageable) {
        return new ResponseEntity<>(notificationService.findManagerNotifications(id, pageable), HttpStatus.OK);
    }

}
