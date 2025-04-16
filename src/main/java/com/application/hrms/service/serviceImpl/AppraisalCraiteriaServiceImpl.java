package com.application.hrms.service.serviceImpl;

import com.application.hrms.JWT.JwtFilter;
import com.application.hrms.JWT.jwtUtil;
import com.application.hrms.POJO.AppraisalCategories;
import com.application.hrms.POJO.AppraisalCriteria;
import com.application.hrms.POJO.Department;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.dao.AppraisalCategoriesDao;
import com.application.hrms.dao.AppraisalCriteriaDao;
import com.application.hrms.dao.DepartmentDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import com.application.hrms.service.AppraisalCategoriesService;
import com.application.hrms.service.AppraisalCriteriaService;
import com.application.hrms.service.DepartmentService;
import com.application.hrms.utils.HrmsUtils;
import com.application.hrms.wrapper.DepartmentWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AppraisalCraiteriaServiceImpl implements AppraisalCriteriaService {

	@Autowired
	AppraisalCriteriaDao appraisalCriteriaDao;

	@Autowired
	AppraisalCategoriesDao appraisalCategoriesDao;

	@Override
	public ResponseEntity<List<AppraisalCriteria>> getAll() {

		List<AppraisalCriteria> list = new ArrayList<AppraisalCriteria>();
		try {
			return new ResponseEntity<List<AppraisalCriteria>>(appraisalCriteriaDao.findAll(), HttpStatus.OK);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<AppraisalCriteria>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<AppraisalCriteria>> getAllActive() {

		List<AppraisalCriteria> list = new ArrayList<AppraisalCriteria>();
		try {
			return new ResponseEntity<List<AppraisalCriteria>>(appraisalCriteriaDao.getAllActive(), HttpStatus.OK);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<AppraisalCriteria>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> update(Map<String, String> requestMap) {
		try {
			Optional<AppraisalCriteria> optional = appraisalCriteriaDao
					.findById(Integer.parseInt(requestMap.get("id")));
			if (optional.isPresent()) {
				appraisalCriteriaDao.updateStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));
				return HrmsUtils.getResponeEntity("AppraisalCategories Status is updated Successfully", HttpStatus.OK);

			} else {
				return HrmsUtils.getResponeEntity("AppraisalCategories id doesn't exist", HttpStatus.OK);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<AppraisalCriteria>> getInfo(Integer id) {
		List<AppraisalCriteria> list = new ArrayList<AppraisalCriteria>();
		try {
			Optional<AppraisalCriteria> optional = appraisalCriteriaDao.findById(id);
			if (optional.isPresent()) {
				return new ResponseEntity<List<AppraisalCriteria>>(appraisalCriteriaDao.getValuesById(id),
						HttpStatus.OK);
			} else {
				return new ResponseEntity<List<AppraisalCriteria>>(list, HttpStatus.UNAUTHORIZED);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<AppraisalCriteria>>(list, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@Override
	public ResponseEntity<String> updateInfo(Integer id, Map<String, String> requestMap) {
		try {
			Optional<AppraisalCriteria> optional = appraisalCriteriaDao.findById(id);
			if (optional.isPresent()) {
				Optional<AppraisalCategories> optional1 = appraisalCategoriesDao.findById(Integer.parseInt(requestMap.get("category")));
				AppraisalCriteria ac = new AppraisalCriteria();
				ac.setName(requestMap.get("name"));
				ac.setMaxmarks(requestMap.get("maxmarks"));
				ac.setTitle(requestMap.get("title"));
				ac.setCategory(optional1.get());
				ac.setId(id);
				appraisalCriteriaDao.save(ac);
				return HrmsUtils.getResponeEntity("AppraisalCategories Info is updated Successfully", HttpStatus.OK);

			} else {
				return HrmsUtils.getResponeEntity("AppraisalCategories id doesn't exist", HttpStatus.OK);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG + "1", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> create(Map<String, String> requestMap) {
		try {
			AppraisalCriteria appraisalCriteria = appraisalCriteriaDao.findByName(requestMap.get("name"));
			if (Objects.isNull(appraisalCriteria)) {
				appraisalCriteriaDao.save(getDepartmentFromMap(requestMap));
				return HrmsUtils.getResponeEntity("Successfully  Created.", HttpStatus.OK);
			} else {
				return HrmsUtils.getResponeEntity("AppraisalCriteria Name already exits.", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private AppraisalCriteria getDepartmentFromMap(Map<String, String> requestMap) {
		Optional<AppraisalCategories> optional = appraisalCategoriesDao.findById(Integer.parseInt(requestMap.get("category")));
		AppraisalCriteria ac = new AppraisalCriteria();
		ac.setName(requestMap.get("name"));
		ac.setTitle(requestMap.get("title"));
		ac.setMaxmarks(requestMap.get("maxmarks"));
		ac.setCategory(optional.get());		
		return ac;
	}

}
