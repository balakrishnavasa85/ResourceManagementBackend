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
import com.application.hrms.service.DepartmentService;
import com.application.hrms.utils.HrmsUtils;
import com.application.hrms.wrapper.DepartmentWrapper;

@RestController
public class DepartmentRestImpl implements DepartmentRest {

	@Autowired
	DepartmentService departmentService;
	@Override
	public ResponseEntity<List<DepartmentWrapper>> getAllDepartment() {
		try {
            return departmentService.getAllDepartment();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<DepartmentWrapper>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
  
	}

	@Override
	public ResponseEntity<List<DepartmentWrapper>> getDepartmentInfo(Integer id) {
		 try {
	            return departmentService.getDepartmentInfo(id);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return new ResponseEntity<List<DepartmentWrapper>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
	
	}

	@Override
	public ResponseEntity<String> update(Map<String, String> requestMap) {
		   try {
	            return departmentService.update(requestMap);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	    
	}

	@Override
	public ResponseEntity<String> updateDepartment(Integer id, Map<String, String> requestMap) {
		 try {
	            return departmentService.updateInfo(id,requestMap);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> create(Map<String, String> requestMap) {
		 try {
	            //System.out.println("inside userRestImpl");
	            return departmentService.create(requestMap);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        //System.out.println("Before return");
	        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
