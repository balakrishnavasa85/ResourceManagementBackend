package com.application.hrms.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.hrms.wrapper.EmpEducationWrapper;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/empeducation")
public interface EmpEducationRest {

	@GetMapping(path = "/getInfo/{id}")
	public ResponseEntity<List<EmpEducationWrapper>> getEmpEducationInfo(@PathVariable Integer id);

	@PutMapping(path = "/updateInfo/{id}")
	public ResponseEntity<String> updateEmpEducation(@PathVariable Integer id,
			@RequestBody(required = true) Map<String, String> requestMap);

	@PostMapping(path = "/create")
	public ResponseEntity<String> create(@RequestBody(required = true) Map<String, String> requestMap);

	@GetMapping(path = "/getEmpEducationInfo/{id}")
	public ResponseEntity<List<EmpEducationWrapper>> getUserEmpEducationInfo(@PathVariable Integer id);
}
