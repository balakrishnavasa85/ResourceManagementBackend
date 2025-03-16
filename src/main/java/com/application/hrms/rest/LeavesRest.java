package com.application.hrms.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.hrms.POJO.Leaves;

@RequestMapping(path = "/leavesbalnce")
public interface LeavesRest {
	
	@PostMapping(path = "/leaveBalanceChange/{id}")
	public ResponseEntity<String> leaveBalanceChange(@PathVariable Integer id, Integer count,
			String requestMap);
	
	@PostMapping(path = "/createleaveBalance/{id}")
	public ResponseEntity<String> createleaveBalance(@PathVariable Integer id);

    @GetMapping(path = "/getInfo/{id}")
    public ResponseEntity<Leaves> getLeavesInfo(@PathVariable Integer id); 
}
