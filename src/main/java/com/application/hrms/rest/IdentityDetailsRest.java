package com.application.hrms.rest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.hrms.POJO.IdentityDetails;

@RequestMapping(path = "/identitydetails")
public interface IdentityDetailsRest {

	@PostMapping(path = "/create/{user}")
	public ResponseEntity<String> createIdentity(@PathVariable Integer user,
			@RequestBody(required = true) Map<String, String> requestMap);
	

	@GetMapping(path = "/getDetails/{user}")
	public ResponseEntity<IdentityDetails> getDetailsIdentity(@PathVariable Integer user);
}
