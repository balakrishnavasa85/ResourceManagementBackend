package com.application.hrms.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.application.hrms.wrapper.RelationWrapper;

public interface RelationService {
		
	ResponseEntity<String> create(Integer id,List<Map<String,String>> requestMap);

	ResponseEntity<List<RelationWrapper>> getUserRelationInfo(Integer id);
}
