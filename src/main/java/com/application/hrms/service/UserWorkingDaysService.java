package com.application.hrms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.application.hrms.POJO.UserWorkingDays;

public interface UserWorkingDaysService {

	ResponseEntity<List<UserWorkingDays>> getPayslip(Integer id, Integer month, Integer year);

	ResponseEntity<List<UserWorkingDays>> getPayslipById(Integer id);

	ResponseEntity<List<UserWorkingDays>> getAllUserSalaryInfo(Integer month, Integer year);

}
