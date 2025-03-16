package com.application.hrms.service;

import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.springframework.http.ResponseEntity;

import com.application.hrms.POJO.TaxSubmission;

public interface TaxSubmissionService {

	ResponseEntity<String> create(Integer id, Map<String, String> requestMap) throws JSONException;

	ResponseEntity<TaxSubmission> getDetails(Integer user);

	ResponseEntity<List<TaxSubmission>> getall();
}
