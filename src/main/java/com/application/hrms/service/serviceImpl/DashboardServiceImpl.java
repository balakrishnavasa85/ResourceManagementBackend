package com.application.hrms.service.serviceImpl;

import com.google.common.base.Strings;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.application.hrms.JWT.CustomerUserDetailsService;
import com.application.hrms.JWT.JwtFilter;
import com.application.hrms.JWT.jwtUtil;
import com.application.hrms.POJO.DeductionGroup;
import com.application.hrms.POJO.Department;
import com.application.hrms.POJO.Designation;
import com.application.hrms.POJO.User;
import com.application.hrms.constents.HrmsConstants;
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
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	UserDao ud;

	@Autowired
	DepartmentDao dpd;

	@Autowired
	DesignationDao dsd;

	@Autowired
	DeductionGroupDao dgd;

	public ResponseEntity<Map> getAll() {
		Map<String, Object> response = new HashMap<>();
		try {
			List<User> user = ud.findAll();
			List<Department> department = dpd.findAll();
			List<Designation> designation = dsd.findAll();
			List<DeductionGroup> deductiongroup = dgd.findAll();
		        response.put("users", user);
		        response.put("departments", department);
		        response.put("designations", designation);
		        response.put("deductiongroups", deductiongroup);
			return new ResponseEntity<Map>(response, HttpStatus.OK);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<Map>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}