package com.application.hrms.rest.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.rest.ApplicationAccessRest;
import com.application.hrms.rest.DashboardRest;
import com.application.hrms.service.ApplicationAccessService;
import com.application.hrms.service.DashboardService;
import com.application.hrms.utils.HrmsUtils;

@RestController
public class ApplicationAccessRestImpl implements ApplicationAccessRest {

	@Autowired
	ApplicationAccessService aaService;
	
	
	@Override
	public ResponseEntity<Map> checkaccess() {
		 try {
	            return aaService.checkaccess();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return new ResponseEntity<Map>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
	  
	
	}

	
}
