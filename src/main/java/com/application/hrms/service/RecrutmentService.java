package com.application.hrms.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.application.hrms.POJO.Recrutment;
import com.application.hrms.wrapper.DepartmentWrapper;

public interface RecrutmentService {  
	
	ResponseEntity<String> createrecrtment(Map<String, String> requestMap);
	
	ResponseEntity<List<Recrutment>> getActive();
}
