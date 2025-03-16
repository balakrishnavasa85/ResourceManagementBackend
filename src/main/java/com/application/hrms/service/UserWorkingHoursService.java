package com.application.hrms.service;

import com.application.hrms.POJO.User;
import com.application.hrms.POJO.UserWorkingDays;
import com.application.hrms.POJO.UserWorkingHours;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserWorkingHoursService {

	ResponseEntity<List<UserWorkingHours>> getByUserId(Integer id); 
	
	ResponseEntity<List<UserWorkingHours>> getByUserIdMonth(Integer id,Integer month,Integer year);
	
	ResponseEntity<Map> generatePayslip();
	

}
