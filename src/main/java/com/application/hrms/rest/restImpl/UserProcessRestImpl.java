package com.application.hrms.rest.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.application.hrms.POJO.User;
import com.application.hrms.POJO.UserProcess;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.rest.UserProcessRest;
import com.application.hrms.service.UserProcessService;
import com.application.hrms.utils.HrmsUtils;
import com.application.hrms.wrapper.UserProcessWrapper;

@RestController
public class UserProcessRestImpl implements UserProcessRest {

	@Autowired
	UserProcessService ups;

	@Override
	public ResponseEntity<List<UserProcess>> check(Map<String,String> requestMap) {
		try {
			return ups.check(requestMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
        return new ResponseEntity((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<UserProcessWrapper>> getList() {
		try {
			return ups.getList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
        return new ResponseEntity((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
