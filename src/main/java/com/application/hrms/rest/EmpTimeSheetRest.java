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

@RequestMapping(path = "/emptimesheet")
public interface EmpTimeSheetRest {
  @PostMapping(path = "/emplogin/{id}")
  public ResponseEntity<String> emplogin(@PathVariable Integer id);
  

  @PostMapping(path = "/emplogout/{id}")
  public ResponseEntity<String> emplogout(@PathVariable Integer id);
  
  @GetMapping(path = "/UserWorkingHours")
  public ResponseEntity<List<Object>> UserWorkingHours();

//	@GetMapping(path = "/get")
//    public ResponseEntity<List<RelationWrapper>> getAllRelation();
//    
//    @GetMapping(path = "/getInfo/{id}")
//    public ResponseEntity<List<RelationWrapper>> getRelationInfo(@PathVariable Integer id);
//
//    @PostMapping(path = "/update")
//    public ResponseEntity<String> update(@RequestBody(required = true) Map<String, String> requestMap);
//    
//    @PutMapping(path = "/updateInfo/{id}")
//    public ResponseEntity<String> updateRelation(@PathVariable Integer id, @RequestBody(required = true) Map<String, String> requestMap);
//
//    @PostMapping(path = "/create")
//    public ResponseEntity<String> create(@RequestBody(required = true) Map<String, String> requestMap);
//    
//
//    @GetMapping(path = "/getRelationInfo/{id}")
//    public ResponseEntity<List<RelationWrapper>> getUserRelationInfo(@PathVariable Integer id);
}
