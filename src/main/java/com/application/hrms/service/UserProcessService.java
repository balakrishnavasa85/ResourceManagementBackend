package com.application.hrms.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.application.hrms.POJO.Tds;
import com.application.hrms.POJO.UserProcess;
import com.application.hrms.wrapper.UserProcessDetailsDTO;
import com.application.hrms.wrapper.UserProcessWrapper;

public interface UserProcessService {

	ResponseEntity<List<UserProcess>> check(Map<String,String> request); 
	
	ResponseEntity<String> updateUserConformation(Map<String, String> requestMap);
	
	ResponseEntity<String> offerInitiate(Map<String, String> requestMap);
	
	ResponseEntity<UserProcess> getyUser(Integer id); 
	
	ResponseEntity<String> updateUserInformation(Map<String, String> requestMap);
	
	ResponseEntity<List<UserProcessDetailsDTO>> getAcceptedUsersList();
	
	ResponseEntity<String> onboard(Integer userProcessId, Integer recrutmentId, Map<String, String> requestMap);
}
