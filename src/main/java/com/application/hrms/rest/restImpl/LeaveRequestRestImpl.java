package com.application.hrms.rest.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.application.hrms.POJO.LeaveRequest;
import com.application.hrms.POJO.User;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.rest.LeaveRequestRest;
import com.application.hrms.rest.LeaveRequestRest;
import com.application.hrms.service.LeaveRequestService;
import com.application.hrms.service.LeavesService;
import com.application.hrms.utils.HrmsUtils;

@RestController
public class LeaveRequestRestImpl implements LeaveRequestRest {

	@Autowired
	LeaveRequestService lrService;

	@Override
	public ResponseEntity<String> leaverequest(Integer id, Map<String, String> requestMap) {
		try {
			return lrService.leaveRequest(id, requestMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> leaverequestapprovereject(List<Map<String, String>> requestMap) {
		try {
//			return null;
			return lrService.leaverequestapprovereject(requestMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@Override
	public ResponseEntity<List<LeaveRequest>> getApplyedLeaveInfo(Integer id) {
		try {
			return lrService.getApplyedLeaveInfo(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	    return new ResponseEntity<List<LeaveRequest>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
	    	}

	@Override
	public ResponseEntity<String> leaverequestcancel(Integer leaverequestid,Map<String, String> requestMap) {
		try {
//			return null;
			return lrService.leaverequestcancel(leaverequestid);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@Override
	public ResponseEntity<List<LeaveRequest>> leaverequestneedtoapprove(Integer id) {
		try {
			return lrService.leaverequestneedtoapprove(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	    return new ResponseEntity<List<LeaveRequest>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
	  }

	@Override
	public ResponseEntity<List<String>> getApplyedLeavesList(Integer id, String fromdate, String todate) {
		try {
			return lrService.getApplyedLeavesList(id,fromdate,todate);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	    return new ResponseEntity<List<String>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
	  }
}
