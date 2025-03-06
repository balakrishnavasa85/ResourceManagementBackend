package com.application.hrms.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.hrms.POJO.EmpExperiance;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/empexperiance")
public interface EmpExperianceRest {

	@GetMapping(path = "/getInfo/{id}")
	public ResponseEntity<List<EmpExperiance>> getEmpExperianceInfo(@PathVariable Integer id);

	@PutMapping(path = "/updateInfo/{id}")
	public ResponseEntity<String> updateEmpExperiance(@PathVariable Integer id,
			@RequestBody(required = true) Map<String, String> requestMap);

	@PostMapping(path = "/create")
	public ResponseEntity<String> create(@RequestBody(required = true) Map<String, String> requestMap);

	@GetMapping(path = "/getEmpExperianceInfo/{id}")
	public ResponseEntity<List<EmpExperiance>> getUserEmpExperianceInfo(@PathVariable Integer id);
}
