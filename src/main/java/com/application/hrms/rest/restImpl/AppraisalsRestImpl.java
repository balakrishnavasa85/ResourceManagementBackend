package com.application.hrms.rest.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.application.hrms.POJO.AppraisalCategories;
import com.application.hrms.POJO.AppraisalCriteria;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.rest.AppraisalCriteriaRest;
import com.application.hrms.rest.AppraisalsRest;
import com.application.hrms.service.AppraisalsService;
import com.application.hrms.service.AppraisalCriteriaService;
import com.application.hrms.utils.HrmsUtils;
import com.application.hrms.wrapper.GoalsetDTO; 

@RestController
public class AppraisalsRestImpl implements AppraisalsRest {

	@Autowired
	AppraisalsService appraisalsService;
	
	@Override
	public ResponseEntity<String> create(Integer id,Map<String, String> requestMap) {
		 try {
//	            System.out.println("inside userRestImpl");
	            return appraisalsService.create(id,requestMap);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        //System.out.println("Before return");
	        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
//	@Override
//	public ResponseEntity<List<AppraisalCriteria>> getAll() {
//		try {
//            return appraisalCriteriaService.getAll();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return new ResponseEntity<List<AppraisalCriteria>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
//  
//	}
//
//	@Override
//	public ResponseEntity<List<AppraisalCriteria>> getInfo(Integer id) {
//		 try {
//				return appraisalCriteriaService.getInfo(id);
//	        } catch (Exception ex) {
//	            ex.printStackTrace();
//	        }
//	        return new ResponseEntity<List<AppraisalCriteria>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
//	
//	}
//
//	@Override
//	public ResponseEntity<String> update(Map<String, String> requestMap) {
//		   try {
//	            return appraisalCriteriaService.update(requestMap);
//	        } catch (Exception ex) {
//	            ex.printStackTrace();
//	        }
//	        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
//	    
//	}
////
//	@Override
//	public ResponseEntity<String> updateInfo(Integer id, Map<String, String> requestMap) {
//		 try {
//	            return appraisalCriteriaService.updateInfo(id,requestMap);
//	        } catch (Exception ex) {
//	            ex.printStackTrace();
//	        }
//	        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
//	}	
//
//	@Override
//	public ResponseEntity<List<AppraisalCriteria>> getAllActive() {
//		try {
//            return appraisalCriteriaService.getAllActive();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return new ResponseEntity<List<AppraisalCriteria>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
//  
//	}
//
//	@Override
//	public ResponseEntity<List<GoalsetDTO>> getGoalSet() {
//		try {
//            return appraisalCriteriaService.getGoalSet();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return new ResponseEntity<List<GoalsetDTO>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
//  }

}
