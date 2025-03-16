package com.application.hrms.rest;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.hrms.POJO.Holidays;

@RequestMapping(path = "/holidays")
public interface HolidaysRest {

	@GetMapping(path = "/getall")
	public ResponseEntity<List<Holidays>> getAll();
	
	@GetMapping(path = "/getByMonthName/{name}")
	public ResponseEntity<List<Holidays>> getByMonthName(@PathVariable Integer name);

	@GetMapping(path = "/getHolidayList/{fromdate}/{todate}")
	public ResponseEntity<List<Holidays>> getHolidayList(@PathVariable String fromdate,@PathVariable String todate);
}
