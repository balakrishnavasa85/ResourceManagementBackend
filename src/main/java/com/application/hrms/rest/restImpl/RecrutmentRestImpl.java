package com.application.hrms.rest.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.application.hrms.POJO.Recrutment;
import com.application.hrms.rest.RecrutmentRest;
import com.application.hrms.service.RecrutmentService;

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
		return new ResponseEntity<String>((MultiValueMap<String, String>) new ArrayList<Object>(),
				HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@Override
	public ResponseEntity<List<Recrutment>> getActive() {
		try {

			return recrutmentService.getActive();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<Recrutment>>((MultiValueMap<String, String>) new ArrayList<Object>(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Recrutment>> getRecruitmentsByUserId(Integer userid) {
		try {

			return recrutmentService.getRecruitmentsByUserId(userid);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<Recrutment>>((MultiValueMap<String, String>) new ArrayList<Object>(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> creatUserProcess(String data, MultipartFile file) {
		try {
			return recrutmentService.creatUserProcess(data,file);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<String>((MultiValueMap<String, String>) new ArrayList<Object>(),
				HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
