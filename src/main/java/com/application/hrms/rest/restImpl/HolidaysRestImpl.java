package com.application.hrms.rest.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.application.hrms.POJO.Holidays;
import com.application.hrms.POJO.Leaves;
import com.application.hrms.POJO.User;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.rest.HolidaysRest;
import com.application.hrms.rest.LeavesRest;
import com.application.hrms.service.LeaveRequestService;
import com.application.hrms.service.HolidaysService;
import com.application.hrms.utils.HrmsUtils;

@RestController
public class HolidaysRestImpl implements HolidaysRest {

	@Autowired
	HolidaysService hsService;

	@Override
	public ResponseEntity<List<Holidays>> getAll() {
		  try {
	            return hsService.getAll();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return new ResponseEntity<List<Holidays>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
	  
	}

	@Override
	public ResponseEntity<List<Holidays>> getByMonthName(Integer name) {
		try {
            return hsService.getByMonthName(name);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<Holidays>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
  
	}

	@Override
	public ResponseEntity<List<Holidays>> getHolidayList(String fromdate, String todate) {
		try {
            return hsService.getHolidayList(fromdate,todate);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<Holidays>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
  
	}

 
}
