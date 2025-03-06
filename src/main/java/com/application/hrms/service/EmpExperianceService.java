package com.application.hrms.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.application.hrms.POJO.EmpExperiance;

public interface EmpExperianceService {
	
	ResponseEntity<List<EmpExperiance>> getEmpExperianceInfo(Integer id); 

	ResponseEntity<String> updateInfo(Integer id, Map<String, String> requestMap);
	
	ResponseEntity<String> create(Map<String,String> requestMap);

	ResponseEntity<List<EmpExperiance>> getUserEmpExperianceInfo(Integer id);
}
