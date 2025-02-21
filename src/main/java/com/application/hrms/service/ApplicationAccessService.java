package com.application.hrms.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.application.hrms.POJO.Holidays;
import com.application.hrms.POJO.Leaves;
import com.application.hrms.wrapper.RelationWrapper;

public interface ApplicationAccessService { 
	
	ResponseEntity<Map> checkaccess();	
	
}
