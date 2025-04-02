package com.application.hrms.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.hrms.POJO.Tds;
import com.application.hrms.POJO.UserProcess;
import com.application.hrms.wrapper.UserProcessWrapper;

@RequestMapping(path = "/usersprocess")
public interface UserProcessRest { 
	
	@GetMapping(path = "/check/{value}")
    public ResponseEntity<List<UserProcess>> check(@RequestBody(required = true) Map<String, String> requestMap);  
	
	@PostMapping(path = "/updateUserConformation")
	public ResponseEntity<String> updateUserConformation(@RequestBody(required = true) Map<String, String> requestMap); 
	
	@PostMapping(path ="/offerInitiate")
	public ResponseEntity<String> offerInitiate(@RequestBody(required = true) Map<String, String> requestMap);
}
