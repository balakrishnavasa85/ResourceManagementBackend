package com.application.hrms.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.application.hrms.wrapper.DeductionGroupWrapper;

public interface DeductionGroupService {
	ResponseEntity<List<DeductionGroupWrapper>> getAll();

    ResponseEntity<String> update(Map<String, String> requestMap);

	ResponseEntity<String> updateInfo(Integer id, Map<String, String> requestMap);

	ResponseEntity<List<DeductionGroupWrapper>> getInfo(Integer id); 
	
	ResponseEntity<String> create(Map<String,String> requestMap);
}
