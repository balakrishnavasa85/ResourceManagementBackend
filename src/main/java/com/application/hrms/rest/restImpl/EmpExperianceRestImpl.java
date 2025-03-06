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
import com.application.hrms.rest.EmpExperianceRest;
import com.application.hrms.service.EmpExperianceService;
import com.application.hrms.utils.HrmsUtils;
import com.application.hrms.POJO.EmpExperiance;

@RestController
public class EmpExperianceRestImpl implements EmpExperianceRest {

	@Autowired
	EmpExperianceService relationService;

	@Override
	public ResponseEntity<List<EmpExperiance>> getEmpExperianceInfo(Integer id) {
		 try {
	            return relationService.getEmpExperianceInfo(id);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return new ResponseEntity<List<EmpExperiance>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
	
	}

	@Override
	public ResponseEntity<String> updateEmpExperiance(Integer id, Map<String, String> requestMap) {
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
	public ResponseEntity<List<EmpExperiance>> getUserEmpExperianceInfo(Integer id) {
		try {
            return relationService.getUserEmpExperianceInfo(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<EmpExperiance>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
  
	}

}
