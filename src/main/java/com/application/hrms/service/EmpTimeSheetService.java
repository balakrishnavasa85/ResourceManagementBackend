package com.application.hrms.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.application.hrms.wrapper.RelationWrapper;

public interface EmpTimeSheetService { 
	
	ResponseEntity<String> emplogin(Integer id); 
	ResponseEntity<String> emplogout(Integer id); 
	ResponseEntity<List<Object>> UserWorkingHours();
}
