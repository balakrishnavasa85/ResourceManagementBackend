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
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.rest.AppraisalCategoriesRest;
import com.application.hrms.service.AppraisalCategoriesService;
import com.application.hrms.utils.HrmsUtils;
import com.application.hrms.wrapper.CategoriesDTO; 

@RestController
public class AppraisalCategoriesRestImpl implements AppraisalCategoriesRest {

	@Autowired
	AppraisalCategoriesService appraisalCategoriesService;
	@Override
	public ResponseEntity<List<CategoriesDTO>> getAll() {
		try {
            return appraisalCategoriesService.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<CategoriesDTO>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
  
	}

	@Override
	public ResponseEntity<List<AppraisalCategories>> getInfo(Integer id) {
		 try {
				return appraisalCategoriesService.getInfo(id);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return new ResponseEntity<List<AppraisalCategories>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
	
	}

	@Override
	public ResponseEntity<String> update(Map<String, String> requestMap) {
		   try {
	            return appraisalCategoriesService.update(requestMap);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	    
	}

	@Override
	public ResponseEntity<String> updateInfo(Integer id, Map<String, String> requestMap) {
		 try {
	            return appraisalCategoriesService.updateInfo(id,requestMap);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> create(Map<String, String> requestMap) {
		 try {
//	            System.out.println("inside userRestImpl");
	            return appraisalCategoriesService.create(requestMap);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        //System.out.println("Before return");
	        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<AppraisalCategories>> getAllActive() {
		try {
            return appraisalCategoriesService.getAllActive();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<AppraisalCategories>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
  
	}

}
