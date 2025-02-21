package com.application.hrms.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.hrms.POJO.Holidays;
import com.application.hrms.POJO.Leaves;
import com.application.hrms.POJO.UserWorkingDays;
import com.application.hrms.POJO.UserWorkingHours;
import com.application.hrms.wrapper.DepartmentWrapper;
import com.application.hrms.wrapper.RelationWrapper;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/userworkinghours")
public interface UserWorkingHoursRest {

	@GetMapping(path = "/getByUserId/{id}")
	public ResponseEntity<List<UserWorkingHours>> getByUserId(@PathVariable Integer id);

	@GetMapping(path = "/getByUserIdMonth/{id}/{month}/{year}")
	public ResponseEntity<List<UserWorkingHours>> getByUserIdMonth(@PathVariable Integer id,@PathVariable Integer month,@PathVariable Integer year);
	
	@GetMapping(path = "/generatePayslip")
	public ResponseEntity<Map> generatePayslip();	
 
}
