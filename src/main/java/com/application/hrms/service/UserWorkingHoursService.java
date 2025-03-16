package com.application.hrms.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.application.hrms.POJO.UserWorkingHours;

public interface UserWorkingHoursService {

	ResponseEntity<List<UserWorkingHours>> getByUserId(Integer id);

	ResponseEntity<List<UserWorkingHours>> getByUserIdMonth(Integer id, Integer month, Integer year);

	ResponseEntity<Map> generatePayslip();

}
