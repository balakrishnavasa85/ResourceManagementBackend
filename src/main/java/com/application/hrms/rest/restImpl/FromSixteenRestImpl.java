package com.application.hrms.rest.restImpl;

import java.io.File; 
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.application.hrms.POJO.FormSixteen;
import com.application.hrms.POJO.LeaveRequest;
import com.application.hrms.POJO.User;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.rest.FromSixteenRest;
import com.application.hrms.rest.LeaveRequestRest;
import com.application.hrms.rest.LeaveRequestRest;
import com.application.hrms.service.FormSixteenService;
import com.application.hrms.service.LeavesService;
import com.application.hrms.utils.HrmsUtils;

@RestController
public class FromSixteenRestImpl implements FromSixteenRest {

	@Autowired
	FormSixteenService formSixteenSerivce;
	
	@Override
	public ResponseEntity<String> uploadForms(String data,List<MultipartFile> file)  {
		try {
			return formSixteenSerivce.uploadForms(data,file);
		} catch (Exception ex) {

			ex.printStackTrace();
		}
	    return new ResponseEntity<String>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
	  }

	@Override
	public ResponseEntity<List<FormSixteen>> form16byid(Integer userid) {
		try {
			return formSixteenSerivce.form16byid(userid);
		} catch (Exception ex) {

			ex.printStackTrace();
		}
	    return new ResponseEntity<List<FormSixteen>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
	} 
}
