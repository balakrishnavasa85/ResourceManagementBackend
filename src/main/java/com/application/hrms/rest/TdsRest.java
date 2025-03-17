package com.application.hrms.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.hrms.POJO.Tds;

@RequestMapping(path = "/tds")
public interface TdsRest {

	@GetMapping(path = "/get")
    public ResponseEntity<List<Tds>> getAll();
	
	@PostMapping(path = "/create")
    public ResponseEntity<String> create(@RequestBody(required = true) List<Map<String, String>> requestMap);
	
	@PostMapping(path = "/delete")
	public ResponseEntity<String> delete();
}
