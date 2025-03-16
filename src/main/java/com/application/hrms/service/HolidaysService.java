package com.application.hrms.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.application.hrms.POJO.Holidays;
import com.application.hrms.POJO.Leaves;
import com.application.hrms.wrapper.RelationWrapper;

public interface HolidaysService { 
	
	ResponseEntity<List<Holidays>> getAll();
	
	ResponseEntity<List<Holidays>> getByMonthName(Integer name);
	
	ResponseEntity<List<Holidays>> getHolidayList(String fromdate, String todate);
	
}
