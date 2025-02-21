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
import com.application.hrms.rest.DepartmentRest;
import com.application.hrms.rest.DesignationRest;
import com.application.hrms.service.DesignationService;
import com.application.hrms.utils.HrmsUtils;
import com.application.hrms.wrapper.DepartmentWrapper;
import com.application.hrms.wrapper.DesignationWrapper;

@RestController
public class DesignationRestImpl implements DesignationRest {

	@Autowired
	DesignationService designationService;
	@Override
	public ResponseEntity<List<DesignationWrapper>> getAll() {
		try {
            return designationService.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<DesignationWrapper>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
  
	}

	@Override
	public ResponseEntity<List<DesignationWrapper>> getInfo(Integer id) {
		 try {
	            return designationService.getInfo(id);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return new ResponseEntity<List<DesignationWrapper>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
	
	}

	@Override
	public ResponseEntity<String> update(Map<String, String> requestMap) {
		   try {
	            return designationService.update(requestMap);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	    
	}

	@Override
	public ResponseEntity<String> updateInfo(Integer id, Map<String, String> requestMap) {
		 try {
	            return designationService.updateInfo(id,requestMap);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> create(Map<String, String> requestMap) {
		 try {
	            //System.out.println("inside userRestImpl");
	            return designationService.create(requestMap);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        //System.out.println("Before return");
	        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
