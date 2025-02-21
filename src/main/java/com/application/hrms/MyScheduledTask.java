package com.application.hrms;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@Component
public class MyScheduledTask {

    // Run this method at a specific time, for example, every day at 10:00 AM
    @Scheduled(cron = "0 46 9 * * ?")
    public void runTaskAtSpecificTime() {
    	
        System.out.println("Scheduled task executed at 9:46 AM every day");
        LocalDate currentDate = LocalDate.now();
        System.out.println("Current Date: " + currentDate);
        LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedYesterday = yesterday.format(formatter);
       
        System.out.println("Yesterday's Date: " + formattedYesterday);
    }
}
