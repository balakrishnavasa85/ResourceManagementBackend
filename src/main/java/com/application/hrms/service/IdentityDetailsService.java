package com.application.hrms.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.application.hrms.POJO.IdentityDetails;

public interface IdentityDetailsService {

	ResponseEntity<String> createIdentity(Integer id, Map<String, String> requestMap);

	ResponseEntity<IdentityDetails> getDetailsIdentity(Integer user);
}
