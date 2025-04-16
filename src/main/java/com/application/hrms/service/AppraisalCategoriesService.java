package com.application.hrms.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.application.hrms.POJO.AppraisalCategories;
import com.application.hrms.wrapper.CategoriesDTO;

public interface AppraisalCategoriesService {
	ResponseEntity<List<CategoriesDTO>> getAll();

	ResponseEntity<List<AppraisalCategories>> getAllActive();

	ResponseEntity<String> update(Map<String, String> requestMap);

	ResponseEntity<String> updateInfo(Integer id, Map<String, String> requestMap);

	ResponseEntity<List<AppraisalCategories>> getInfo(Integer id);

	ResponseEntity<String> create(Map<String, String> requestMap);
}
