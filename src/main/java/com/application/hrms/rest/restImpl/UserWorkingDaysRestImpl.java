package com.application.hrms.rest.restImpl;

import com.application.hrms.JWT.jwtUtil;
import com.application.hrms.POJO.UserWorkingDays;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.rest.UserWorkingDaysRest;
import com.application.hrms.service.UserWorkingDaysService;
import com.application.hrms.utils.HrmsUtils;
import com.application.hrms.wrapper.UserWorkingDaysWrapper;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@RestController
public class UserWorkingDaysRestImpl implements UserWorkingDaysRest {

    @Autowired
    UserWorkingDaysService userWorkingDaysService;

   

@Override
public ResponseEntity<List<UserWorkingDays>> getPayslip(Integer id, Integer month, Integer year) {
	 try {
	  	   return userWorkingDaysService.getPayslip(id,month,year);
	     } catch (Exception ex) {
	         ex.printStackTrace();
	     }
	     return new ResponseEntity<List<UserWorkingDays>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
		
}



@Override
public ResponseEntity<List<UserWorkingDays>> getPayslipById(Integer id) {
	 try {
	  	   return userWorkingDaysService.getPayslipById(id);
	     } catch (Exception ex) {
	         ex.printStackTrace();
	     }
	     return new ResponseEntity<List<UserWorkingDays>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
}



@Override
public ResponseEntity<List<UserWorkingDays>> getAllUserSalaryInfo(Integer month, Integer year) {
	try {
	  	   return userWorkingDaysService.getAllUserSalaryInfo(month,year);
	     } catch (Exception ex) {
	         ex.printStackTrace();
	     }
	     return new ResponseEntity<List<UserWorkingDays>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
}
 
}
