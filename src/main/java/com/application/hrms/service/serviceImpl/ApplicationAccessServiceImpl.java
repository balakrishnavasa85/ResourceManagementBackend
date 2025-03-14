package com.application.hrms.service.serviceImpl;

import com.google.common.base.Strings;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.application.hrms.JWT.CustomerUserDetailsService;
import com.application.hrms.JWT.JwtFilter;
import com.application.hrms.JWT.jwtUtil;
import com.application.hrms.POJO.ApplicationAccess;
import com.application.hrms.POJO.DeductionGroup;
import com.application.hrms.POJO.Department;
import com.application.hrms.POJO.Designation;
import com.application.hrms.POJO.User;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.service.ApplicationAccessService;
import com.application.hrms.service.DashboardService;
import com.application.hrms.dao.*;
import com.application.hrms.utils.HrmsUtils;

import lombok.extern.slf4j.Slf4j;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.application.hrms.service.serviceImpl.EmpTImeSheetServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Service
public class ApplicationAccessServiceImpl implements ApplicationAccessService {

	@Autowired
	ApplicationAccessDao aad;

	@Override
	public ResponseEntity<Map> checkaccess(String name) {
		Map<String, Object> response = new HashMap<>();
		try {
			List<ApplicationAccess> validAccesses = aad.findAccessValidToday(name);
			if (validAccesses.size() > 0) {
				response.put("validAccesses", true);
			} else {
				response.put("validAccesses", false);
			}
			return new ResponseEntity<Map>(response, HttpStatus.OK);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<Map>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<ApplicationAccess>> allcheckaccess() {

		List<ApplicationAccess> list = new ArrayList<ApplicationAccess>();
		try {
			return new ResponseEntity<List<ApplicationAccess>>(aad.findExceptMain(), HttpStatus.OK);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<ApplicationAccess>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> updateData(Map<String, String> requestMap) {

		Optional<ApplicationAccess> optional = aad.findById(Integer.parseInt(requestMap.get("id")));
		try {
			if (optional.isPresent()) {
				ApplicationAccess aa = new ApplicationAccess();
				aa.setName(requestMap.get("name"));
				aa.setFromaccess(requestMap.get("fromaccess"));
				aa.setUptoaccess(requestMap.get("uptoaccess"));
				aa.setId(Integer.parseInt(requestMap.get("id")));
				aa.setValue(requestMap.get("value"));
				aad.save(aa);

				return HrmsUtils.getResponeEntity("Item Info is updated Successfully", HttpStatus.OK);
			} else {

				return HrmsUtils.getResponeEntity("Item doesn't exist", HttpStatus.OK);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<ApplicationAccess>> getDetails(String name) {

		List<ApplicationAccess> list = new ArrayList<ApplicationAccess>();
		try {
			list = aad.findAccessValidToday(name);
			return new ResponseEntity<List<ApplicationAccess>>(list, HttpStatus.OK);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<ApplicationAccess>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}