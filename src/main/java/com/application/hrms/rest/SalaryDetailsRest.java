package com.application.hrms.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.hrms.POJO.SalaryDetails;

@RequestMapping(path = "/salarydetails")
public interface SalaryDetailsRest {

	@PostMapping(path = "/create/{user}")
	public ResponseEntity<String> create(@PathVariable Integer user,
			@RequestBody(required = true) Map<String, String> requestMap);	

	@GetMapping(path = "/getDetails/{user}")
	public ResponseEntity<SalaryDetails> getDetails(@PathVariable Integer user);
	  
	  @PostMapping(path ="/UploadData")
	  public ResponseEntity<String> uploadUsers(@RequestBody List<Map<String, Object>> data ); 
}
