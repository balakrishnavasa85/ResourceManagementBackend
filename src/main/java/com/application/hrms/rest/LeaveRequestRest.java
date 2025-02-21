package com.application.hrms.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.hrms.POJO.LeaveRequest;
import com.application.hrms.wrapper.DepartmentWrapper;
import com.application.hrms.wrapper.RelationWrapper;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/leaverequest")
public interface LeaveRequestRest {

	@PostMapping(path = "/leaverequest/{id}")
	public ResponseEntity<String> leaverequest(@PathVariable Integer id,
			@RequestBody(required = true) Map<String, String> requestMap);
	
	@PostMapping(path = "/leaverequestapprovereject")
	public ResponseEntity<String> leaverequestapprovereject(@RequestBody List<Map<String, String>> requestMapList);

	@GetMapping(path = "/getApplyedLeaveInfo/{id}")
	public ResponseEntity<List<LeaveRequest>> getApplyedLeaveInfo(@PathVariable Integer id);
	
	@PostMapping(path = "/leaverequestcancel/{leaverequestid}")
	public ResponseEntity<String> leaverequestcancel(@PathVariable Integer leaverequestid,Map<String, String> requestMap);
	
	@GetMapping(path = "/leaverequestneedtoapprove/{id}")
	public ResponseEntity<List<LeaveRequest>> leaverequestneedtoapprove(@PathVariable Integer id);
	

	@GetMapping(path = "/getApplyedLeavesList/{id}/{fromdate}/{todate}")
	public ResponseEntity<List<String>> getApplyedLeavesList(@PathVariable Integer id,@PathVariable String fromdate,@PathVariable String todate);

//	@GetMapping(path = "/get")
//    public ResponseEntity<List<RelationWrapper>> getAllRelation();
//    
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
