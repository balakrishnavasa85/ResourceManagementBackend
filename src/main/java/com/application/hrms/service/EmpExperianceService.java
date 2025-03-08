package com.application.hrms.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.application.hrms.POJO.EmpExperiance;

public interface EmpExperianceService {
	
	ResponseEntity<List<EmpExperiance>> getEmpExperianceInfo(Integer id); 

	ResponseEntity<String> updateInfo(Integer id, Map<String, String> requestMap);
	
	ResponseEntity<String> create(Integer id,List<String> experiances, List<MultipartFile> files);

	ResponseEntity<List<EmpExperiance>> getUserEmpExperianceInfo(Integer id);
}
