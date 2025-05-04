package com.application.hrms.service.serviceImpl;

import com.application.hrms.JWT.JwtFilter;
import com.application.hrms.JWT.jwtUtil;
import com.application.hrms.POJO.AppraisalCategories;
import com.application.hrms.POJO.AppraisalCriteria;
import com.application.hrms.POJO.Appraisals;
import com.application.hrms.POJO.Department;
import com.application.hrms.POJO.User;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.dao.AppraisalCategoriesDao;
import com.application.hrms.dao.AppraisalCriteriaDao;
import com.application.hrms.dao.AppraisalsDao;
import com.application.hrms.dao.DepartmentDao;
import com.application.hrms.dao.UserDao;

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
import com.application.hrms.service.AppraisalsService;
import com.application.hrms.service.DepartmentService;
import com.application.hrms.utils.HrmsUtils;
import com.application.hrms.wrapper.DepartmentWrapper;
import com.application.hrms.wrapper.GoalsetDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AppraisalsServiceImpl implements AppraisalsService {

	@Autowired
	UserDao userDao;

	@Autowired
	AppraisalsDao appraisalsDao;

//	@Override
//	public ResponseEntity<List<AppraisalCriteria>> getAll() {
//
//		List<AppraisalCriteria> list = new ArrayList<AppraisalCriteria>();
//		try {
//			return new ResponseEntity<List<AppraisalCriteria>>(appraisalCriteriaDao.findAll(), HttpStatus.OK);
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		return new ResponseEntity<List<AppraisalCriteria>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//
//	@Override
//	public ResponseEntity<List<AppraisalCriteria>> getAllActive() {
//
//		List<AppraisalCriteria> list = new ArrayList<AppraisalCriteria>();
//		try {
//			return new ResponseEntity<List<AppraisalCriteria>>(appraisalCriteriaDao.getAllActive(), HttpStatus.OK);
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		return new ResponseEntity<List<AppraisalCriteria>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//
//	@Override
//	public ResponseEntity<String> update(Map<String, String> requestMap) {
//		try {
//			Optional<AppraisalCriteria> optional = appraisalCriteriaDao
//					.findById(Integer.parseInt(requestMap.get("id")));
//			if (optional.isPresent()) {
//				appraisalCriteriaDao.updateStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));
//				return HrmsUtils.getResponeEntity("AppraisalCategories Status is updated Successfully", HttpStatus.OK);
//
//			} else {
//				return HrmsUtils.getResponeEntity("AppraisalCategories id doesn't exist", HttpStatus.OK);
//			}
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//
//	@Override
//	public ResponseEntity<List<AppraisalCriteria>> getInfo(Integer id) {
//		List<AppraisalCriteria> list = new ArrayList<AppraisalCriteria>();
//		try {
//			Optional<AppraisalCriteria> optional = appraisalCriteriaDao.findById(id);
//			if (optional.isPresent()) {
//				return new ResponseEntity<List<AppraisalCriteria>>(appraisalCriteriaDao.getValuesById(id),
//						HttpStatus.OK);
//			} else {
//				return new ResponseEntity<List<AppraisalCriteria>>(list, HttpStatus.UNAUTHORIZED);
//			}
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		return new ResponseEntity<List<AppraisalCriteria>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
//
//	}
//
//	@Override
//	public ResponseEntity<String> updateInfo(Integer id, Map<String, String> requestMap) {
//		try {
//			Optional<AppraisalCriteria> optional = appraisalCriteriaDao.findById(id);
//			if (optional.isPresent()) {
//				Optional<AppraisalCategories> optional1 = appraisalCategoriesDao.findById(Integer.parseInt(requestMap.get("category")));
//				AppraisalCriteria ac = new AppraisalCriteria();
//				ac.setName(requestMap.get("name"));
//				ac.setMaxmarks(requestMap.get("maxmarks"));
//				ac.setTitle(requestMap.get("title"));
//				ac.setCategory(optional1.get());
//				ac.setId(id);
//				appraisalCriteriaDao.save(ac);
//				return HrmsUtils.getResponeEntity("AppraisalCategories Info is updated Successfully", HttpStatus.OK);
//
//			} else {
//				return HrmsUtils.getResponeEntity("AppraisalCategories id doesn't exist", HttpStatus.OK);
//			}
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG + "1", HttpStatus.INTERNAL_SERVER_ERROR);
//	}

	@Override
	public ResponseEntity<String> create(Integer id, Map<String, String> requestMap) {
		try {
		Optional<User> userInfo = userDao.findById(id);
			if (userInfo.isPresent()) {
				appraisalsDao.save(getFromMap(requestMap,userInfo.get()));
				return HrmsUtils.getResponeEntity("Successfully  Created.", HttpStatus.OK);
			} else {
				return HrmsUtils.getResponeEntity("AppraisalCriteria Name already exits.", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private Appraisals getFromMap(Map<String, String> requestMap,User userInfo) {
		Appraisals ac = new Appraisals();
		ac.setUser(userInfo);
		ac.setData(requestMap.get("data"));
		ac.setComments(requestMap.get("comments"));
		ac.setScore(requestMap.get(ac));
		return ac;
	}

//	@Override
//	public ResponseEntity<List<GoalsetDTO>> getGoalSet() {
//		List<GoalsetDTO> list = new ArrayList<GoalsetDTO>();
//		try { 
//				List<Object[]> results = appraisalCriteriaDao.getGoalset();
//				List<Object> goals = new ArrayList<>();
//				for (Object[] result : results) {					
//					Integer cid =  (Integer) result[0];
//					String came = (String) result[1];
//					Integer ca_id =  (Integer) result[2];
//					String ca_title = (String) result[3];
//					String ca_name = (String) result[4];
//					Integer marks =  Integer.valueOf((String) result[5]);
//					GoalsetDTO gst = new GoalsetDTO();
//					gst.setCategoryId(cid);
//					gst.setCategoryName(came);
//					gst.setCriteriaId(ca_id);
//					gst.setCriteriaTitle(ca_title);
//					gst.setCriteriaName(ca_name);
//					gst.setCriteriaMaxmarks(marks);
//					list.add(gst);					
//				}							
//				return new ResponseEntity<List<GoalsetDTO>>(list, HttpStatus.OK);
//			 
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//		return new ResponseEntity<List<GoalsetDTO>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
//	}

}
