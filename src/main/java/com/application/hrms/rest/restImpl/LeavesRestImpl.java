package com.application.hrms.rest.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.application.hrms.POJO.Leaves;
import com.application.hrms.POJO.User;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.rest.LeavesRest;
import com.application.hrms.service.LeaveRequestService;
import com.application.hrms.service.LeavesService;
import com.application.hrms.utils.HrmsUtils;

@RestController
public class LeavesRestImpl implements LeavesRest {

	@Autowired
	LeavesService lsService;

	@Override
	public ResponseEntity<String> leaveBalanceChange(Integer id, Integer count, String requestMap) {
		try {
			return lsService.leaveBalanceChange(id,count, requestMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Override
	public ResponseEntity<String> createleaveBalance(Integer id) {
		try {
			return lsService.createleaveBalance(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<Leaves> getLeavesInfo(Integer id) { 
			  try {
		            return lsService.getLeavesInfo(id);
		        } catch (Exception ex) {
		            ex.printStackTrace();
		        }
		        return new ResponseEntity<Leaves>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
		  
		
	}

 
}
