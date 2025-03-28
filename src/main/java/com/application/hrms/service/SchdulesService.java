package com.application.hrms.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONArray;
import org.springframework.http.ResponseEntity;

import com.application.hrms.POJO.Schdules;

public interface SchdulesService {
	ResponseEntity<String> create(Map<String, String> requestMap);
	
	ResponseEntity<Boolean> checkUserInterviews(Integer id);
	
	ResponseEntity<List<Schdules>> checkInterviewDetailsById(Integer id);
}
