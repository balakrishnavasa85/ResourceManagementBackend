package com.application.hrms.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.hrms.POJO.AppraisalCategories;
import com.application.hrms.POJO.AppraisalCriteria;
import com.application.hrms.wrapper.DepartmentWrapper;
import com.application.hrms.wrapper.GoalsetDTO;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/appraisals")
public interface AppraisalsRest {

	@PostMapping(path = "/create/{id}")
	public ResponseEntity<String> create(@PathVariable Integer id,@RequestBody(required = true) Map<String, String> requestMap);

//	@GetMapping(path = "/get")
//	public ResponseEntity<List<AppraisalCriteria>> getAll();
//
//	@GetMapping(path = "/getActive")
//	public ResponseEntity<List<AppraisalCriteria>> getAllActive();
//
//	@GetMapping(path = "/getInfo/{id}")
//	public ResponseEntity<List<AppraisalCriteria>> getInfo(@PathVariable Integer id);
//
//	@PostMapping(path = "/update")
//	public ResponseEntity<String> update(@RequestBody(required = true) Map<String, String> requestMap);
//
//	@PostMapping(path = "/updateInfo/{id}")
//	public ResponseEntity<String> updateInfo(@PathVariable Integer id,
//			@RequestBody(required = true) Map<String, String> requestMap);
//
//	@GetMapping(path = "/getGoalSet")
//	public ResponseEntity<List<GoalsetDTO>> getGoalSet();

}
