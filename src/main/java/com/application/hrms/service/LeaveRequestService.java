package com.application.hrms.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.application.hrms.POJO.LeaveRequest;
import com.application.hrms.wrapper.RelationWrapper;

public interface LeaveRequestService { 
	
	ResponseEntity<String> leaveRequest(Integer id, Map<String, String> requestMap);
	
	ResponseEntity<String> leaverequestapprovereject(List<Map<String, String>> requestMap);
	
	ResponseEntity<List<LeaveRequest>> getApplyedLeaveInfo(Integer id);
	
	ResponseEntity<String> leaverequestcancel(Integer leaverequestid);
	
	ResponseEntity<List<LeaveRequest>> leaverequestneedtoapprove(Integer id);
	
	ResponseEntity<List<String>> getApplyedLeavesList(Integer id, String fromdate, String todate);
	
}
