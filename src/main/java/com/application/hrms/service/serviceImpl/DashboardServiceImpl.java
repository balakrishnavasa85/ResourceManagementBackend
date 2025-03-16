package com.application.hrms.service.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.application.hrms.POJO.DeductionGroup;
import com.application.hrms.POJO.Department;
import com.application.hrms.POJO.Designation;
import com.application.hrms.POJO.User;
import com.application.hrms.dao.DeductionGroupDao;
import com.application.hrms.dao.DepartmentDao;
import com.application.hrms.dao.DesignationDao;
import com.application.hrms.dao.UserDao;
import com.application.hrms.service.DashboardService;

import lombok.extern.slf4j.Slf4j;

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