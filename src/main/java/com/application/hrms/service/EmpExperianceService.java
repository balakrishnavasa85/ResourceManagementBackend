package com.application.hrms.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.application.hrms.wrapper.EmpExperianceWrapper;

public interface EmpExperianceService {
	
	ResponseEntity<List<EmpExperianceWrapper>> getEmpExperianceInfo(Integer id); 

	ResponseEntity<String> updateInfo(Integer id, Map<String, String> requestMap);
	
	ResponseEntity<String> create(Map<String,String> requestMap);

	ResponseEntity<List<EmpExperianceWrapper>> getUserEmpExperianceInfo(Integer id);
}
