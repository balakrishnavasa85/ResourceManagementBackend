package com.application.hrms.rest.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.application.hrms.POJO.SalaryDetails;
import com.application.hrms.POJO.UserWorkingHours;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.rest.DepartmentRest;
import com.application.hrms.rest.SalaryDetailsRest;
import com.application.hrms.service.DepartmentService;
import com.application.hrms.service.SalaryDetailsService;
import com.application.hrms.utils.HrmsUtils;
import com.application.hrms.wrapper.DepartmentWrapper;

@RestController
public class SalaryDetailsRestImpl implements SalaryDetailsRest {

	@Autowired
	SalaryDetailsService salaryDetailsService; 

	@Override
	public ResponseEntity<String> create(Integer user, Map<String, String> requestMap) {
		 try {
	            return salaryDetailsService.create(user,requestMap);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<SalaryDetails> getDetails(Integer user) {
		try {
            return salaryDetailsService.getDetails(user);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		return new ResponseEntity<SalaryDetails>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);		
	}

	@Override
	public ResponseEntity<String> uploadUsers(List<Map<String, Object>> data) {
		try {
            return salaryDetailsService.uploadUsers(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		return new ResponseEntity<String>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
