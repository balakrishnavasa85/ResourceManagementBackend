package com.application.hrms.service.serviceImpl;

import com.application.hrms.JWT.JwtFilter;
import com.application.hrms.JWT.jwtUtil;
import com.application.hrms.POJO.EmpEducation;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.dao.EmpEducationDao;

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

import com.application.hrms.service.EmpEducationService;
import com.application.hrms.utils.HrmsUtils;
import com.application.hrms.wrapper.EmpEducationWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmpEducationServiceImpl implements EmpEducationService {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	com.application.hrms.JWT.jwtUtil jwtUtil;

	@Autowired
	JwtFilter jwtFilter;

	@Autowired
	EmpEducationDao relationDao;

	@Override
	public ResponseEntity<List<EmpEducationWrapper>> getEmpEducationInfo(Integer id) {
		List<EmpEducationWrapper> list = new ArrayList<EmpEducationWrapper>();
		try {
//			if (jwtFilter.isAdmin()) {
				Optional<EmpEducation> optional = relationDao.findById(id);
				if (optional.isPresent()) {
					return new ResponseEntity<List<EmpEducationWrapper>>(relationDao.getEmpEducationById(id),
							HttpStatus.OK);
				} else {
					return new ResponseEntity<List<EmpEducationWrapper>>(list, HttpStatus.UNAUTHORIZED);
				}
//			} else {
//				return new ResponseEntity<List<EmpEducationWrapper>>(list, HttpStatus.UNAUTHORIZED);
//			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<EmpEducationWrapper>>(list, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@Override
	public ResponseEntity<String> updateInfo(Integer id, Map<String, String> requestMap) {
		try {
//			if (jwtFilter.isAdmin()) {
				Optional<EmpEducation> optional = relationDao.findById(id);
				if (optional.isPresent()) {
					EmpEducation relation = new EmpEducation();
					relation.setStudy(requestMap.get("study"));
					relation.setDuration(requestMap.get("duration"));
					relation.setJoiningDate(requestMap.get("joiningdate"));
					relation.setReleaveDate(requestMap.get("releavedate"));
					relation.setUser(Integer.parseInt(requestMap.get("user")));
					relation.setId(id);
					relationDao.save(relation);
					return HrmsUtils.getResponeEntity("Relation Info is updated Successfully", HttpStatus.OK);

				} else {
					return HrmsUtils.getResponeEntity("Relation id doesn't exist", HttpStatus.OK);
				}
//			} else {
//				return HrmsUtils.getResponeEntity(HrmsConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
//			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> create(Map<String, String> requestMap) {
		try {
			EmpEducation Relation = relationDao.findByName(requestMap.get("study"));
			if (Objects.isNull(Relation)) {
				relationDao.save(getEmpEducationFromMap(requestMap));
				return HrmsUtils.getResponeEntity("Successfully  Created.", HttpStatus.OK);
			} else {
				return HrmsUtils.getResponeEntity("Departmnt Name already exits.", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private EmpEducation getEmpEducationFromMap(Map<String, String> requestMap) {
		EmpEducation relation = new EmpEducation();
		relation.setStudy(requestMap.get("study"));
		relation.setDuration(requestMap.get("duration"));
		relation.setJoiningDate(requestMap.get("joiningdate"));
		relation.setReleaveDate(requestMap.get("releavedate"));
		relation.setUser(Integer.parseInt(requestMap.get("user")));
		return relation;
	}

	@Override
	public ResponseEntity<List<EmpEducationWrapper>> getUserEmpEducationInfo(Integer id) {
		List<EmpEducationWrapper> list = new ArrayList<EmpEducationWrapper>();
		try {
//			if (jwtFilter.isAdmin()) {
				List<EmpEducationWrapper> optional = relationDao.getEmpEducationByUserId(id);
				if (optional.size() > 0) {
					return new ResponseEntity<List<EmpEducationWrapper>>(optional, HttpStatus.OK);
				} else {
					return new ResponseEntity<List<EmpEducationWrapper>>(list, HttpStatus.OK);
				}
//			} else {
//				return new ResponseEntity<List<EmpEducationWrapper>>(list, HttpStatus.UNAUTHORIZED);
//			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<EmpEducationWrapper>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
