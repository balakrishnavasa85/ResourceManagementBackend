package com.application.hrms.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.hrms.wrapper.DesignationWrapper;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/designation")
public interface DesignationRest {

	@GetMapping(path = "/get")
    public ResponseEntity<List<DesignationWrapper>> getAll();
    
    @GetMapping(path = "/getInfo/{id}")
    public ResponseEntity<List<DesignationWrapper>> getInfo(@PathVariable Integer id);

    @PostMapping(path = "/update")
    public ResponseEntity<String> update(@RequestBody(required = true) Map<String, String> requestMap);
    
    @PostMapping(path = "/updateInfo/{id}")
    public ResponseEntity<String> updateInfo(@PathVariable Integer id, @RequestBody(required = true) Map<String, String> requestMap);

    @PostMapping(path = "/create")
    public ResponseEntity<String> create(@RequestBody(required = true) Map<String, String> requestMap);
}
