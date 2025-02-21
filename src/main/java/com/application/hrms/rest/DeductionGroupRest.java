package com.application.hrms.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.hrms.wrapper.DeductionGroupWrapper;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/deductiongroup")
public interface DeductionGroupRest {

	@GetMapping(path = "/get")
    public ResponseEntity<List<DeductionGroupWrapper>> getAll();
    
    @GetMapping(path = "/getInfo/{id}")
    public ResponseEntity<List<DeductionGroupWrapper>> getInfo(@PathVariable Integer id);

    @PostMapping(path = "/update")
    public ResponseEntity<String> update(@RequestBody(required = true) Map<String, String> requestMap);
    
    @PutMapping(path = "/updateInfo/{id}")
    public ResponseEntity<String> updateInfo(@PathVariable Integer id, @RequestBody(required = true) Map<String, String> requestMap);

    @PostMapping(path = "/creategroup")
    public ResponseEntity<String> creategroup(@RequestBody(required = true) Map<String, String> requestMap);
}
