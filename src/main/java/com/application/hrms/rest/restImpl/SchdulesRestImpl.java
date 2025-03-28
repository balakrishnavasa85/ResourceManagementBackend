package com.application.hrms.rest.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.application.hrms.POJO.Holidays;
import com.application.hrms.POJO.Leaves;
import com.application.hrms.POJO.Schdules;
import com.application.hrms.POJO.User;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.rest.HolidaysRest;
import com.application.hrms.rest.LeavesRest;
import com.application.hrms.rest.SchdulesRest;
import com.application.hrms.service.SchdulesService;
import com.application.hrms.service.HolidaysService;
import com.application.hrms.utils.HrmsUtils;

@RestController
public class SchdulesRestImpl implements SchdulesRest {

	@Autowired
	SchdulesService schdulesService;

	@Override
	public ResponseEntity<String> create(Map<String,String> requestMap) {
		try {
            return schdulesService.create(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<String>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);  
	}

	@Override
	public ResponseEntity<Boolean> checkUserInterviews(Integer id) {
		try {
            return schdulesService.checkUserInterviews(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<Boolean>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Schdules>> checkInterviewDetailsById(Integer id) {

		try {
            return schdulesService.checkInterviewDetailsById(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<Schdules>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

 
}
