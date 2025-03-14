package com.application.hrms.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.hrms.POJO.ApplicationAccess;
import com.application.hrms.POJO.Leaves;
import com.application.hrms.wrapper.DepartmentWrapper;
import com.application.hrms.wrapper.RelationWrapper;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/applicationaccess")
public interface ApplicationAccessRest {
//
	@GetMapping(path = "/checkaccess/{name}")
	public ResponseEntity<Map> checkaccess(@PathVariable String name); 

	@GetMapping(path = "/allcheckaccess")
	public ResponseEntity<List<ApplicationAccess>> allcheckaccess(); 	

	@PostMapping(path = "/updateData")
	public ResponseEntity<String> updateData(@RequestBody(required = true) Map<String, String> requestMap); 
	
	@GetMapping(path ="/getDetails/{name}")
	public ResponseEntity<List<ApplicationAccess>> getDetails(@PathVariable String name);
}
