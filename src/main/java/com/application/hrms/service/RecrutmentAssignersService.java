package com.application.hrms.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.application.hrms.POJO.Recrutment;
import com.application.hrms.wrapper.DepartmentWrapper;

public interface RecrutmentAssignersService {  
	
	ResponseEntity<String> createrecrtmentassign(Map<String, String> requestMap);
	
	ResponseEntity<List<Recrutment>> getById(Integer id);
}
