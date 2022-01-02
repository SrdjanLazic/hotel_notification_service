package com.raf.hotelnotificationservice.controller;

import com.raf.hotelnotificationservice.dto.NotificationDto;
import com.raf.hotelnotificationservice.dto.NotificationTypeDto;
import com.raf.hotelnotificationservice.dto.NotificationTypeUpdateDto;
import com.raf.hotelnotificationservice.security.CheckSecurity;
import com.raf.hotelnotificationservice.service.NotificationService;
import com.raf.hotelnotificationservice.service.NotificationTypeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/notification-type")
public class NotificationTypeController {

    private NotificationTypeService notificationTypeService;

    public NotificationTypeController(NotificationTypeService notificationTypeService) {
        this.notificationTypeService = notificationTypeService;
    }

    @GetMapping
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Page<NotificationTypeDto>> getAllNotificationTypes(@RequestHeader("Authorization") String authorization,
                                                                         Pageable pageable) {
        return new ResponseEntity<>(notificationTypeService.findAll(pageable), HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<NotificationTypeDto> updateNotificationType(@RequestHeader("Authorization") String authorization,
                                                                      @PathVariable("id") Long id, @RequestBody @Valid NotificationTypeUpdateDto notificationTypeUpdateDto) {
        return ResponseEntity.ok(notificationTypeService.updateNotificationType(id, notificationTypeUpdateDto));
    }

    @DeleteMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Void> deleteNotificationType(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id) {
        notificationTypeService.deleteNotificationType(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
