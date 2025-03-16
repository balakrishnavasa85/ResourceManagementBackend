package com.application.hrms.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.application.hrms.wrapper.RelationWrapper;

public interface RelationService {
	ResponseEntity<List<RelationWrapper>> getAllRelation();
		
    ResponseEntity<String> update(Map<String, String> requestMap);

	ResponseEntity<String> updateInfo(Integer id, Map<String, String> requestMap);

	ResponseEntity<List<RelationWrapper>> getRelationInfo(Integer id); 
	
	ResponseEntity<String> create(Map<String,String> requestMap);

	ResponseEntity<List<RelationWrapper>> getUserRelationInfo(Integer id);
}
