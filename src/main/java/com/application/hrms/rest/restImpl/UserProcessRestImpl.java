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
import com.application.hrms.wrapper.RecruitmentDetailsDTO;
import com.application.hrms.wrapper.UserProcessDetailsDTO;
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
	public ResponseEntity<String> updateUserConformation(Map<String, String> requestMap) {
		try {
			return ups.updateUserConformation(requestMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> offerInitiate(Map<String, String> requestMap) {
		try {
			return ups.offerInitiate(requestMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<UserProcess> getyUser(Integer id) {
		try {
			return ups.getyUser(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
        return new ResponseEntity((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> updateUserInformation(Map<String, String> requestMap) {
		try {
			return ups.updateUserInformation(requestMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<UserProcessDetailsDTO>> getAcceptedUsersList() {
		try {
			return ups.getAcceptedUsersList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<UserProcessDetailsDTO>>((MultiValueMap<String, String>) new ArrayList<Object>(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> onboard(Integer userProcessId, Integer recrutmentId, Map<String, String> requestMap) {
		try {
			return ups.onboard(userProcessId,recrutmentId,requestMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
