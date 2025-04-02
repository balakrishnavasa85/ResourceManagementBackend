package com.application.hrms.rest.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.application.hrms.POJO.Schdules;
import com.application.hrms.rest.SchdulesRest;
import com.application.hrms.service.SchdulesService;
import com.application.hrms.wrapper.RecruitmentDetailsDTO;

@RestController
public class SchdulesRestImpl implements SchdulesRest {

	@Autowired
	SchdulesService schdulesService;

	@Override
	public ResponseEntity<String> create(Map<String, String> requestMap) {
		try {
			return schdulesService.create(requestMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<String>((MultiValueMap<String, String>) new ArrayList<Object>(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<Boolean> checkUserInterviews(Integer id) {
		try {
			return schdulesService.checkUserInterviews(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<Boolean>((MultiValueMap<String, String>) new ArrayList<Object>(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<RecruitmentDetailsDTO>> checkInterviewDetailsById(Integer id) {

		try {
			return schdulesService.checkInterviewDetailsById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<RecruitmentDetailsDTO>>((MultiValueMap<String, String>) new ArrayList<Object>(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> updateFeedback(Map<String, String> requestMap) {
		try {
			return schdulesService.updateFeedback(requestMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<String>((MultiValueMap<String, String>) new ArrayList<Object>(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Schdules>> checkPreviousHistory(Integer id) {

		try {
			return schdulesService.checkPreviousHistory(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<Schdules>>((MultiValueMap<String, String>) new ArrayList<Object>(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<RecruitmentDetailsDTO>> selectedUser() {
		try {
			return schdulesService.selectedUser();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<RecruitmentDetailsDTO>>((MultiValueMap<String, String>) new ArrayList<Object>(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
