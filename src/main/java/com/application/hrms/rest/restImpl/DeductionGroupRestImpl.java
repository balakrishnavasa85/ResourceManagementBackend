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
import com.application.hrms.rest.DeductionGroupRest;
import com.application.hrms.service.DeductionGroupService;
import com.application.hrms.utils.HrmsUtils;
import com.application.hrms.wrapper.DeductionGroupWrapper;

@RestController
public class DeductionGroupRestImpl implements DeductionGroupRest {

	@Autowired
	DeductionGroupService deductionGroupService;
	@Override
	public ResponseEntity<List<DeductionGroupWrapper>> getAll() {
		try {
            return deductionGroupService.getAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<DeductionGroupWrapper>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
  
	}

	@Override
	public ResponseEntity<List<DeductionGroupWrapper>> getInfo(Integer id) {
		 try {
	            return deductionGroupService.getInfo(id);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return new ResponseEntity<List<DeductionGroupWrapper>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
	
	}

	@Override
	public ResponseEntity<String> update(Map<String, String> requestMap) {
		   try {
	            return deductionGroupService.update(requestMap);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	    
	}

	@Override
	public ResponseEntity<String> updateInfo(Integer id, Map<String, String> requestMap) {
		 try {
	            return deductionGroupService.updateInfo(id,requestMap);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> creategroup(Map<String, String> requestMap) {
		 try {
//	            System.out.println("inside userRestImpl");
	            return deductionGroupService.create(requestMap);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        //System.out.println("Before return");
	        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
