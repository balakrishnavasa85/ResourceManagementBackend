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
import com.application.hrms.service.DepartmentService;
import com.application.hrms.utils.HrmsUtils;
import com.application.hrms.wrapper.CategoriesDTO;
import com.application.hrms.wrapper.DepartmentWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AppraisalCategoriesServiceImpl implements AppraisalCategoriesService {

	@Autowired
	AppraisalCategoriesDao appraisalCategoriesDao;

	@Autowired
	AppraisalCriteriaDao appraisalCriteriaDao;
	
	@Override
	public ResponseEntity<List<CategoriesDTO>> getAll() {

		List<CategoriesDTO> list = new ArrayList<CategoriesDTO>();
		try {
			List<AppraisalCategories> listnew = appraisalCategoriesDao.findAll();
			List<CategoriesDTO> dtolist = new ArrayList<CategoriesDTO>();
			
			for (AppraisalCategories category : listnew) {
				CategoriesDTO dto = new CategoriesDTO();
				dto.setId(category.getId());
				dto.setName(category.getName());
				dto.setStatus(category.getStatus());
				dto.setMaxmarks(category.getMaxmarks());
				List<AppraisalCriteria> listc = appraisalCriteriaDao.loadCriterias(category.getId());
				dto.setCriteriaList(listc);
				dtolist.add(dto);				
			}			
			return new ResponseEntity<List<CategoriesDTO>>(dtolist, HttpStatus.OK);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<CategoriesDTO>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<AppraisalCategories>> getAllActive() {

		List<AppraisalCategories> list = new ArrayList<AppraisalCategories>();
		try {
			return new ResponseEntity<List<AppraisalCategories>>(appraisalCategoriesDao.getAllActive(), HttpStatus.OK);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<AppraisalCategories>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> update(Map<String, String> requestMap) {
		try {
			Optional<AppraisalCategories> optional = appraisalCategoriesDao
					.findById(Integer.parseInt(requestMap.get("id")));
			if (optional.isPresent()) {
				appraisalCategoriesDao.updateStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));
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
	public ResponseEntity<List<AppraisalCategories>> getInfo(Integer id) {
		List<AppraisalCategories> list = new ArrayList<AppraisalCategories>();
		try {
			Optional<AppraisalCategories> optional = appraisalCategoriesDao.findById(id);
			if (optional.isPresent()) {
				return new ResponseEntity<List<AppraisalCategories>>(appraisalCategoriesDao.getValuesById(id),
						HttpStatus.OK);
			} else {
				return new ResponseEntity<List<AppraisalCategories>>(list, HttpStatus.UNAUTHORIZED);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<AppraisalCategories>>(list, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@Override
	public ResponseEntity<String> updateInfo(Integer id, Map<String, String> requestMap) {
		try {
			Optional<AppraisalCategories> optional = appraisalCategoriesDao.findById(id);
			if (optional.isPresent()) {
				AppraisalCategories department = new AppraisalCategories();
				department.setName(requestMap.get("name"));
				department.setMaxmarks(requestMap.get("maxmarks"));
				department.setStatus(optional.get().getStatus());
//				department.setStatus(optional.get().getStatus());)
				department.setId(id);
				appraisalCategoriesDao.save(department);
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
			AppraisalCategories appraisalCategories = appraisalCategoriesDao.findByName(requestMap.get("name"));
			if (Objects.isNull(appraisalCategories)) {
				appraisalCategoriesDao.save(getDepartmentFromMap(requestMap));
				return HrmsUtils.getResponeEntity("Successfully  Created.", HttpStatus.OK);
			} else {
				return HrmsUtils.getResponeEntity("AppraisalCategories Name already exits.", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private AppraisalCategories getDepartmentFromMap(Map<String, String> requestMap) {
		AppraisalCategories ac = new AppraisalCategories();
		ac.setName(requestMap.get("name"));
		ac.setMaxmarks(requestMap.get("maxmarks"));
		return ac;
	}

}
