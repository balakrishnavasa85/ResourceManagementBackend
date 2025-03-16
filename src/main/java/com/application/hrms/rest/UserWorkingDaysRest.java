package com.application.hrms.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.hrms.POJO.UserWorkingDays;

@RequestMapping(path = "/userworkingdays")
public interface UserWorkingDaysRest {

	@GetMapping(path = "/getPayslip/{id}/{month}/{year}")
	public ResponseEntity<List<UserWorkingDays>> getPayslip(@PathVariable Integer id, @PathVariable Integer month,
			@PathVariable Integer year);

	@GetMapping(path = "/getPayslipByid/{id}")
	public ResponseEntity<List<UserWorkingDays>> getPayslipById(@PathVariable Integer id);

	@GetMapping(path = "/getAllUserSalaryInfo/{month}/{year}")
	public ResponseEntity<List<UserWorkingDays>> getAllUserSalaryInfo(@PathVariable Integer month,
			@PathVariable Integer year);

}
