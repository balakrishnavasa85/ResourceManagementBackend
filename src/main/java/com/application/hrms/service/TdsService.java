package com.application.hrms.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.application.hrms.POJO.Tds;

public interface TdsService {
		
	ResponseEntity<String> create(List<Map<String,String>> requestMap);

	ResponseEntity<List<Tds>> getAll();
	
	ResponseEntity<String> delete();
}
