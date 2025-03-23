package com.application.hrms.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.application.hrms.JWT.JwtFilter;
import com.application.hrms.JWT.jwtUtil;
import com.application.hrms.POJO.Department;
import com.application.hrms.POJO.Recrutment;
import com.application.hrms.POJO.Tds;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.dao.DepartmentDao;
import com.application.hrms.dao.RecrutmentDao;
import com.application.hrms.dao.TdsDao;
import com.application.hrms.service.RecrutmentService;
import com.application.hrms.service.TdsService;
import com.application.hrms.utils.HrmsUtils;
import com.application.hrms.wrapper.DepartmentWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecrutmentServiceImpl implements RecrutmentService {

	@Autowired
	RecrutmentDao recrutmentDao;

	@Autowired
	DepartmentDao departmentDao;
	
	@Autowired
	JwtFilter jwtFilter;

//	@Override
//	public ResponseEntity<List<Tds>> getAll() {
//		List<Tds> list = new ArrayList<Tds>();
//		try {
//			return new ResponseEntity<List<Tds>>(tdsDao.findAll(), HttpStatus.OK);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		return new ResponseEntity<List<Tds>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//
//	@Override
//	public ResponseEntity<String> delete() {
//		try {
//			 tdsDao.deleteAll();
//				return HrmsUtils.getResponeEntity("Information Deleted.", HttpStatus.OK);
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
//	}

	@Override
	public ResponseEntity<String> createrecrtment(Map<String, String> requestMap) {
		try {
			String title = requestMap.get("title");
			Optional<Recrutment> rinfo = recrutmentDao.findByTitle(title);
			if (jwtFilter.isAdmin()) {
			if (!rinfo.isPresent()) {
				Recrutment rdata = new Recrutment();
				rdata.setTitle(title);
				rdata.setBudget(requestMap.get("budget"));
				rdata.setDescription(requestMap.get("description"));
				rdata.setNoofpositions(Integer.parseInt(requestMap.get("noofpositions")));
				rdata.setStatus("y");
				Department dInfo = departmentDao
						.getDepartmentInfoById(Integer.parseInt(requestMap.get("department_id")));
				rdata.setDepartment(dInfo);
				recrutmentDao.save(rdata);
				return HrmsUtils.getResponeEntity("Recrutment Datails Created.", HttpStatus.OK);
			} else {
				return HrmsUtils.getResponeEntity("Email already exits.", HttpStatus.BAD_REQUEST);
			}} else {
				return HrmsUtils.getResponeEntity(HrmsConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Recrutment>> getActive() {
		List<Recrutment> list = new ArrayList<Recrutment>();
		try {
			return new ResponseEntity<List<Recrutment>>(recrutmentDao.getAllActive(), HttpStatus.OK);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<Recrutment>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
