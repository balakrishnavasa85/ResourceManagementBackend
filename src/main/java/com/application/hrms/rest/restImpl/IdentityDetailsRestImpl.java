package com.application.hrms.rest.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.application.hrms.POJO.IdentityDetails;
import com.application.hrms.POJO.SalaryDetails;
import com.application.hrms.POJO.UserWorkingHours;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.rest.DepartmentRest;
import com.application.hrms.rest.IdentityDetailsRest;
import com.application.hrms.rest.SalaryDetailsRest;
import com.application.hrms.service.DepartmentService;
import com.application.hrms.service.IdentityDetailsService;
import com.application.hrms.utils.HrmsUtils;
import com.application.hrms.wrapper.DepartmentWrapper;

@RestController
public class IdentityDetailsRestImpl implements IdentityDetailsRest {

	@Autowired
	IdentityDetailsService identityDetailsService; 

	@Override
	public ResponseEntity<String> createIdentity(Integer user, Map<String, String> requestMap) {
		 try {
	            return identityDetailsService.createIdentity(user,requestMap);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<IdentityDetails> getDetailsIdentity(Integer user) {
		try {
            return identityDetailsService.getDetailsIdentity(user);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		return new ResponseEntity<IdentityDetails>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
