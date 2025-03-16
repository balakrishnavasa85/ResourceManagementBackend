package com.application.hrms.rest.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.application.hrms.POJO.User;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.rest.EmpTimeSheetRest;
import com.application.hrms.service.EmpTimeSheetService;
import com.application.hrms.utils.HrmsUtils;

@RestController
public class EmpTimeSheetRestImpl implements EmpTimeSheetRest {

	@Autowired
	EmpTimeSheetService etsService;

	@Override
	public ResponseEntity<String> emplogin(Integer id) {
		 try {
	            //System.out.println("inside userRestImpl");
	            return etsService.emplogin(id);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        //System.out.println("Before return");
	        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@Override
	public ResponseEntity<String> emplogout(Integer id) {
		 try {
	            //System.out.println("inside userRestImpl");
	            return etsService.emplogout(id);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        //System.out.println("Before return");
	        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@Override
	public ResponseEntity<List<Object>> UserWorkingHours() {
		try { 
            return etsService.UserWorkingHours();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<Object>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> uploadUsers(List<Map<String, Object>> data) {
		try { 
            return etsService.uploadUsers(data);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<String>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
