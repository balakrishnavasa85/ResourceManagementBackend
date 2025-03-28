package com.application.hrms.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.application.hrms.POJO.Tds;
import com.application.hrms.POJO.UserProcess;
import com.application.hrms.wrapper.UserProcessWrapper;

public interface UserProcessService {

	ResponseEntity<List<UserProcess>> check(Map<String,String> request);
	
	ResponseEntity<List<UserProcessWrapper>> getList();
}
