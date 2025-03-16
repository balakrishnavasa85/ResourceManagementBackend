package com.application.hrms.rest.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.application.hrms.POJO.TaxSubmission;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.rest.TaxSubmissionRest;
import com.application.hrms.service.TaxSubmissionService;
import com.application.hrms.utils.HrmsUtils;

@RestController
public class TaxSubmissionRestImpl implements TaxSubmissionRest {

	@Autowired
	TaxSubmissionService tss; 

	@Override
	public ResponseEntity<String> create(Integer user, Map<String, String> requestMap) {
		 try {
	            return tss.create(user,requestMap);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	 

	@Override
	public ResponseEntity<TaxSubmission> getDetails(Integer user) {
		try {
            return tss.getDetails(user);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		return new ResponseEntity<TaxSubmission>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
		  }

	@Override
	public ResponseEntity<List<TaxSubmission>> getall() {
		try {
            return tss.getall();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		return new ResponseEntity<List<TaxSubmission>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
		  }


}
