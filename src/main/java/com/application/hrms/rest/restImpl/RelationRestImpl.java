package com.application.hrms.rest.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.rest.RelationRest;
import com.application.hrms.service.RelationService;
import com.application.hrms.utils.HrmsUtils;
import com.application.hrms.wrapper.RelationWrapper;

@RestController
public class RelationRestImpl implements RelationRest {

	@Autowired
	RelationService relationService;
	@Override
	public ResponseEntity<List<RelationWrapper>> getAllRelation() {
		try {
            return relationService.getAllRelation();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<RelationWrapper>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
  
	}

	@Override
	public ResponseEntity<List<RelationWrapper>> getRelationInfo(Integer id) {
		 try {
	            return relationService.getRelationInfo(id);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return new ResponseEntity<List<RelationWrapper>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
	
	}

	@Override
	public ResponseEntity<String> update(Map<String, String> requestMap) {
		   try {
	            return relationService.update(requestMap);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	    
	}

	@Override
	public ResponseEntity<String> updateRelation(Integer id, Map<String, String> requestMap) {
		 try {
	            return relationService.updateInfo(id,requestMap);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> create(Map<String, String> requestMap) {
		 try {
	            //System.out.println("inside userRestImpl");
	            return relationService.create(requestMap);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        //System.out.println("Before return");
	        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<RelationWrapper>> getUserRelationInfo(Integer id) {
		try {
            return relationService.getUserRelationInfo(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<RelationWrapper>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
  
	}

}
