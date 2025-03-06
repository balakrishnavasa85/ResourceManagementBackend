package com.application.hrms.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.application.hrms.POJO.EmpEducation;

public interface EmpEducationService {
	
	ResponseEntity<List<EmpEducation>> getEmpEducationInfo(Integer id); 

	ResponseEntity<String> updateInfo(Integer id, Map<String, String> requestMap);
	
	ResponseEntity<String> create(Integer id,List<String> educations,List<MultipartFile> files);

	ResponseEntity<List<EmpEducation>> getUserEmpEducationInfo(Integer id);
}
