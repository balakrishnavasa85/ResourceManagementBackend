package com.application.hrms.service;

import org.springframework.http.ResponseEntity;

import com.application.hrms.POJO.Leaves;

public interface LeavesService {

	ResponseEntity<String> leaveBalanceChange(Integer id, Integer count, String requestMap);

	ResponseEntity<String> createleaveBalance(Integer id);

	ResponseEntity<Leaves> getLeavesInfo(Integer id);

}
