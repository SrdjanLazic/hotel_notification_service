package com.raf.hotelnotificationservice.service.impl;

import com.raf.hotelnotificationservice.domain.Notification;
import com.raf.hotelnotificationservice.dto.ClientDto;
import com.raf.hotelnotificationservice.dto.ManagerDto;
import com.raf.hotelnotificationservice.dto.NotificationDto;
import com.raf.hotelnotificationservice.exception.NotFoundException;
import com.raf.hotelnotificationservice.mapper.NotificationMapper;
import com.raf.hotelnotificationservice.repository.NotificationRepository;
import com.raf.hotelnotificationservice.service.NotificationService;
import org.apache.tomcat.jni.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    private NotificationRepository notificationRepository;
    private NotificationMapper notificationMapper;
    private RestTemplate userServiceRestTemplate;

    public NotificationServiceImpl(NotificationRepository notificationRepository, NotificationMapper notificationMapper, RestTemplate userServiceRestTemplate) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
        this.userServiceRestTemplate = userServiceRestTemplate;
    }

    @Override
    public Page<NotificationDto> findAll(Pageable pageable) {
        return notificationRepository.findAll(pageable)
                .map(notificationMapper::notificationToNotificationDto);
    }

    @Override
    public Page<NotificationDto> findClientNotifications(Long id, Pageable pageable) {
        ResponseEntity<ClientDto> clientDtoResponseEntity = null;
        try {
            clientDtoResponseEntity = userServiceRestTemplate.exchange("/client/"
                    + id, HttpMethod.GET, null, ClientDto.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND))
                throw new NotFoundException(String.format("Projection with id: %d not found.", id));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String email = clientDtoResponseEntity.getBody().getEmail();


        List<Notification> allNotifications = notificationRepository.findAll();
        List<NotificationDto> foundNotifications = new ArrayList<>();

        for(Notification notification : allNotifications){
            if (notification.getEmail().equalsIgnoreCase(email)){
                foundNotifications.add(notificationMapper.notificationToNotificationDto(notification));
            }
        }
        Page<NotificationDto> notificationDtoPage = new PageImpl<>(foundNotifications);
        return notificationDtoPage;
    }

    @Override
    public Page<NotificationDto> findManagerNotifications(Long id, Pageable pageable) {
        ResponseEntity<ManagerDto> managerDtoResponseEntity = null;
        try {
            managerDtoResponseEntity = userServiceRestTemplate.exchange("/manager/"
                    + id, HttpMethod.GET, null, ManagerDto.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND))
                throw new NotFoundException(String.format("Projection with id: %d not found.", id));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String email = managerDtoResponseEntity.getBody().getEmail();


        List<Notification> allNotifications = notificationRepository.findAll();
        List<NotificationDto> foundNotifications = new ArrayList<>();

        for(Notification notification : allNotifications){
            if (notification.getEmail().equalsIgnoreCase(email)){
                foundNotifications.add(notificationMapper.notificationToNotificationDto(notification));
            }
        }
        Page<NotificationDto> notificationDtoPage = new PageImpl<>(foundNotifications);
        return notificationDtoPage;
    }

    @Override
    public Page<NotificationDto> findByEmail(String email, Pageable pageable) {
        List<Notification> notificationList;
        notificationList = notificationRepository.findNotificationByEmail(email);

        List<NotificationDto> foundNotifications = new ArrayList<>();

        for(Notification notification : notificationList)
            foundNotifications.add(notificationMapper.notificationToNotificationDto(notification));

        Page<NotificationDto> notificationDtoPage = new PageImpl<>(foundNotifications);
        return notificationDtoPage;
    }

    @Override
    public Page<NotificationDto> findByType(String type, Pageable pageable) {
        List<Notification> notificationList;
        notificationList = notificationRepository.findAll();


        List<Notification> foundNotifications = new ArrayList<>();

        for(Notification notification: notificationList){
            if(notification.getType().getType().toString().equalsIgnoreCase(type)){
                foundNotifications.add(notification);
            }
        }

        List<NotificationDto> toReturn = new ArrayList<>();

        for(Notification notification : foundNotifications)
            toReturn.add(notificationMapper.notificationToNotificationDto(notification));

        Page<NotificationDto> notificationDtoPage = new PageImpl<>(toReturn);
        return notificationDtoPage;
    }

    @Override
    public Page<NotificationDto> findBetweenDates(String date1, String date2, Pageable pageable) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date1Formatted = LocalDate.parse(date1, dateTimeFormatter);
        LocalDate date2Formatted = LocalDate.parse(date2, dateTimeFormatter);


        List<Notification> notificationList = new ArrayList<>();
        notificationList = notificationRepository.findAll();

        List<Notification> foundNotifications = new ArrayList<>();

        for(Notification notification: notificationList){
            LocalDateTime ldt = notification.getInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            LocalDate notificationDateFormatted = LocalDate.parse(ldt.format(dateTimeFormatter), dateTimeFormatter);

            if(notificationDateFormatted.isAfter(date1Formatted) && notificationDateFormatted.isBefore(date2Formatted)){
                foundNotifications.add(notification);
            }

        }

        List<NotificationDto> toReturn = new ArrayList<>();

        for(Notification notification : foundNotifications)
            toReturn.add(notificationMapper.notificationToNotificationDto(notification));

        Page<NotificationDto> notificationDtoPage = new PageImpl<>(toReturn);
        return notificationDtoPage;
    }


}
