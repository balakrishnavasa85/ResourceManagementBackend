package com.application.hrms.service;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.application.hrms.POJO.FormSixteen;

public interface FormSixteenService { 
	
	ResponseEntity<String> uploadForms(String data,List<MultipartFile> file) throws JSONException,IOException;
	
	ResponseEntity<List<FormSixteen>> form16byid(Integer userid);
	
}
