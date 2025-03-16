package com.application.hrms.rest.restImpl;

import com.application.hrms.JWT.jwtUtil;
import com.application.hrms.POJO.Holidays;
import com.application.hrms.POJO.User;
import com.application.hrms.POJO.UserWorkingDays;
import com.application.hrms.POJO.UserWorkingHours;
import com.application.hrms.constents.HrmsConstants;  
import com.application.hrms.rest.UserRest;
import com.application.hrms.rest.UserWorkingHoursRest;
import com.application.hrms.service.UserService;
import com.application.hrms.service.UserWorkingHoursService;
import com.application.hrms.utils.HrmsUtils;
import com.application.hrms.wrapper.UserWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;

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
public class UserWorkingHoursRestImpl implements UserWorkingHoursRest {

    @Autowired
    UserWorkingHoursService uwhs;

   @Override
	public ResponseEntity<List<UserWorkingHours>> getByUserId(Integer id) {
       try {
    	   return uwhs.getByUserId(id);
       } catch (Exception ex) {
           ex.printStackTrace();
       }
       return new ResponseEntity<List<UserWorkingHours>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

@Override
public ResponseEntity<List<UserWorkingHours>> getByUserIdMonth(Integer id, Integer month,Integer year) {
	 try {
  	   return uwhs.getByUserIdMonth(id,month,year);
     } catch (Exception ex) {
         ex.printStackTrace();
     }
     return new ResponseEntity<List<UserWorkingHours>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

@Override
public ResponseEntity<Map> generatePayslip() {
	try {
	  	   return uwhs.generatePayslip();
	     } catch (Exception ex) {
	         ex.printStackTrace();
	     }
	     return new ResponseEntity<Map>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
 
}
