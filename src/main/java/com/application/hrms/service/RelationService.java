package com.application.hrms.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.application.hrms.POJO.Relation;

public interface RelationService {

	ResponseEntity<String> create(Map<String, String> requestMap);

	ResponseEntity<List<Relation>> getUserRelationInfo(Integer id);
}
