package com.application.hrms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.application.hrms.service.EmpTimeSheetService;
import com.application.hrms.service.UserWorkingHoursService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class MyScheduledTask {

	@Autowired
	EmpTimeSheetService etss;

	@Autowired
	UserWorkingHoursService uwhs;

	@Scheduled(cron = "0 5 0 * * ?")
	public void daySheet() {

		etss.UserWorkingHours();
	}

	@Scheduled(cron = "0 0 1 26 * ?")
	public void monthSheet() {
		uwhs.generatePayslip();
	}
}
