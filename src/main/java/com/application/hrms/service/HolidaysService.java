package com.application.hrms.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.application.hrms.POJO.Holidays;

public interface HolidaysService {

	ResponseEntity<List<Holidays>> getAll();

	ResponseEntity<List<Holidays>> getByMonthName(Integer name);

	ResponseEntity<List<Holidays>> getHolidayList(String fromdate, String todate);

}
