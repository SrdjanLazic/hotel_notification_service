package com.raf.hotelnotificationservice.runner;

import com.raf.hotelnotificationservice.repository.NotificationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"default"})
@Component
public class TestDataRunner implements CommandLineRunner {

    private NotificationRepository notificationRepository;

    public TestDataRunner(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void run(String... args) throws Exception {


    }
}