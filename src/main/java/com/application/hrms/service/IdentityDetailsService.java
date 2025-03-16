package com.application.hrms.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.application.hrms.POJO.IdentityDetails;
import com.application.hrms.POJO.SalaryDetails;
import com.application.hrms.wrapper.DepartmentWrapper;

public interface IdentityDetailsService {
	
	ResponseEntity<String> createIdentity(Integer id,Map<String,String> requestMap);
	
	ResponseEntity<IdentityDetails> getDetailsIdentity(Integer user);
}
