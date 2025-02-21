package com.application.hrms.service;

import com.application.hrms.POJO.UserWorkingDays;
import com.application.hrms.wrapper.UserWorkingDaysWrapper;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserWorkingDaysService {

	ResponseEntity<List<UserWorkingDays>> getPayslip(Integer id,Integer month,Integer year);
	
	ResponseEntity<List<UserWorkingDays>> getPayslipById(Integer id);

}
