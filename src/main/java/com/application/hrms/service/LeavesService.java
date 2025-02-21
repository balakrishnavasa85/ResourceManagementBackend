package com.application.hrms.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.application.hrms.POJO.Leaves;
import com.application.hrms.wrapper.RelationWrapper;

public interface LeavesService { 
	
	ResponseEntity<String> leaveBalanceChange(Integer id, Integer count, String requestMap);
	
	ResponseEntity<String> createleaveBalance(Integer id);	
	
	ResponseEntity<Leaves> getLeavesInfo(Integer id);	
	
}
