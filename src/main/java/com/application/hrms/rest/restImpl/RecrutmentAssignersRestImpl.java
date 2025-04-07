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
import com.application.hrms.POJO.RecrutmentAssigners;
import com.application.hrms.POJO.User;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.rest.DepartmentRest;
import com.application.hrms.rest.RecrutmentAssignersRest;
import com.application.hrms.rest.RecrutmentRest;
import com.application.hrms.service.DepartmentService;
import com.application.hrms.service.RecrutmentAssignersService;
import com.application.hrms.service.RecrutmentService;
import com.application.hrms.utils.HrmsUtils;
import com.application.hrms.wrapper.DepartmentWrapper;

@RestController
public class RecrutmentAssignersRestImpl implements RecrutmentAssignersRest {

	@Autowired
	RecrutmentAssignersService recrutmentService;
	 


	@Override
	public ResponseEntity<String> createrecrtmentassign(Map<String, String> requestMap) {
		try {
            return recrutmentService.createrecrtmentassign(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<String>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}



//	@Override
//	public ResponseEntity<RecrutmentAssigners> getById(Integer id) {
//		// TODO Auto-generated method stub
//		return null;
//	}



	@Override
	public ResponseEntity<List<Recrutment>> getById(Integer id) {
		try {
     	   
//        	JSONObject data = jwtUtil.extractUserIdNew(authorizationHeader);
            
            return recrutmentService.getById(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<Recrutment>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
