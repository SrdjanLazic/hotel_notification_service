package com.raf.hotelnotificationservice.runner;

import com.raf.hotelnotificationservice.domain.NotificationType;
import com.raf.hotelnotificationservice.domain.Type;
import com.raf.hotelnotificationservice.repository.NotificationRepository;
import com.raf.hotelnotificationservice.repository.NotificationTypeRepository;
import javassist.NotFoundException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"default"})
@Component
public class TestDataRunner implements CommandLineRunner {

    private NotificationTypeRepository notificationTypeRepository;

    public TestDataRunner(NotificationTypeRepository notificationTypeRepository) {
        this.notificationTypeRepository = notificationTypeRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        notificationTypeRepository.save(new NotificationType(Type.EMAIL_VERIFICATION,"Hi, %s %s. To complete your registration, please click the following link: http://localhost:8080/api/%s/verifyMail/%s."));

    }

}