package com.application.hrms.rest.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.application.hrms.POJO.Recrutment;
import com.application.hrms.POJO.User;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.rest.DepartmentRest;
import com.application.hrms.rest.RecrutmentRest;
import com.application.hrms.service.DepartmentService;
import com.application.hrms.service.RecrutmentService;
import com.application.hrms.utils.HrmsUtils;
import com.application.hrms.wrapper.DepartmentWrapper;

@RestController
public class RecrutmentRestImpl implements RecrutmentRest {

	@Autowired
	RecrutmentService recrutmentService;
	 


	@Override
	public ResponseEntity<String> createrecrtment(Map<String, String> requestMap) {
		try {
            return recrutmentService.createrecrtment(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<String>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);

	}



	@Override
	public ResponseEntity<List<Recrutment>> getActive() {
		try {
     	   
//        	JSONObject data = jwtUtil.extractUserIdNew(authorizationHeader);
            
            return recrutmentService.getActive();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<Recrutment>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
