package com.application.hrms.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.application.hrms.wrapper.DesignationWrapper;

public interface DesignationService {
	ResponseEntity<List<DesignationWrapper>> getAll();

    ResponseEntity<String> update(Map<String, String> requestMap);

	ResponseEntity<String> updateInfo(Integer id, Map<String, String> requestMap);

	ResponseEntity<List<DesignationWrapper>> getInfo(Integer id); 
	
	ResponseEntity<String> create(Map<String,String> requestMap);
}
