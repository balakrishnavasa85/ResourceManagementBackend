package com.application.hrms.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.application.hrms.wrapper.DepartmentWrapper;

public interface DepartmentService {
	ResponseEntity<List<DepartmentWrapper>> getAllDepartment();

    ResponseEntity<String> update(Map<String, String> requestMap);

	ResponseEntity<String> updateInfo(Integer id, Map<String, String> requestMap);

	ResponseEntity<List<DepartmentWrapper>> getDepartmentInfo(Integer id); 
	
	ResponseEntity<String> create(Map<String,String> requestMap);
}
