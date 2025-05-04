package com.application.hrms.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.application.hrms.POJO.AppraisalCategories;
import com.application.hrms.POJO.AppraisalCriteria;
import com.application.hrms.wrapper.GoalsetDTO;

public interface AppraisalsService {
	ResponseEntity<String> create(Integer id, Map<String, String> requestMap);
	
//	ResponseEntity<List<AppraisalCriteria>> getAll();
////
//	ResponseEntity<List<AppraisalCriteria>> getAllActive();
////
//	ResponseEntity<String> update(Map<String, String> requestMap);
////
//	ResponseEntity<String> updateInfo(Integer id, Map<String, String> requestMap);
////
//	ResponseEntity<List<AppraisalCriteria>> getInfo(Integer id);
//	
//	ResponseEntity<List<GoalsetDTO>> getGoalSet();
}
