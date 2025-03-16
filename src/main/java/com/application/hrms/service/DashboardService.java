package com.application.hrms.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface DashboardService { 
	
	ResponseEntity<Map> getAll();	
	
}
