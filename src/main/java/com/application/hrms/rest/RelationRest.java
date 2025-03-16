package com.application.hrms.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.hrms.wrapper.DepartmentWrapper;
import com.application.hrms.wrapper.RelationWrapper;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/relation")
public interface RelationRest {

	@PostMapping(path = "/create/{id}")
    public ResponseEntity<String> create(@PathVariable Integer id,@RequestBody(required = true) List<Map<String, String>> requestMap);
    

    @GetMapping(path = "/getRelationInfo/{id}")
    public ResponseEntity<List<RelationWrapper>> getUserRelationInfo(@PathVariable Integer id);
}
