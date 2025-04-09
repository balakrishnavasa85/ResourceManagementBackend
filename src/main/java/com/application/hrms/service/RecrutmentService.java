package com.application.hrms.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.application.hrms.POJO.Recrutment;
import com.application.hrms.wrapper.DepartmentWrapper;

public interface RecrutmentService {  
	
	ResponseEntity<String> createrecrtment(Map<String, String> requestMap);
	
	ResponseEntity<List<Recrutment>> getActive();
	
	ResponseEntity<List<Recrutment>> getRecruitmentsByUserId(Integer userid);
	
	ResponseEntity<String> creatUserProcess(String data, MultipartFile file) throws JSONException, IOException, MessagingException;
}
