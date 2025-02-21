package com.application.hrms.service.serviceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.management.ObjectName;

import java.util.*;
import java.util.List;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.application.hrms.POJO.Department;
import com.application.hrms.POJO.EmpTimeSheet;
import com.application.hrms.POJO.LeaveRequest;
import com.application.hrms.POJO.Relation;
import com.application.hrms.POJO.User;
import com.application.hrms.dao.EmpTImeSheetDao;
import com.application.hrms.dao.LeaveRequestDao;
import com.application.hrms.dao.UserDao;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.service.LeaveRequestService;
import com.application.hrms.service.LeavesService;
import com.application.hrms.utils.HrmsUtils;
import com.application.hrms.wrapper.DepartmentWrapper;

import java.time.Duration;
import java.time.LocalDate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

	@Autowired
	UserDao userdao;

	@Autowired
	LeaveRequestDao lrd;

	@Autowired
	LeavesService ls;

	@Override
	public ResponseEntity<String> leaveRequest(Integer id, Map<String, String> requestMap) {
		try {
			Optional<User> useri = userdao.findById(id);
			if (useri.isPresent()) {

//				userdao.updateLoginStatus("1", id);
						LeaveRequest ets = new LeaveRequest();
						ets.setStatus(requestMap.get("status"));
						ets.setUser(id);
						ets.setUsername(useri.get().getName());
						ets.setReason(requestMap.get("reason"));
						ets.setComment("");
						User userm = userdao.getUserDetailById(Integer.parseInt(useri.get().getReporting()));
						ets.setApprovedBy(userm.getId());
						ets.setApprover(userm.getName());
						ets.setRequestFromDate(requestMap.get("requestfromdate"));
						ets.setRequestToDate(requestMap.get("requesttodate"));
						ets.setNoofdays(Integer.parseInt(requestMap.get("noofdays")));
						lrd.save(ets);

				return HrmsUtils.getResponeEntity("Successfully Leave Request Submited.", HttpStatus.OK);
			} else {
				return HrmsUtils.getResponeEntity("Leave Request Not Submited", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@Override
	public ResponseEntity<String> leaverequestapprovereject(List<Map<String, String>> requestMapList) {
		try {
		
			JSONArray jsonArray = new JSONArray();
      for (Map<String, String> requestMap : requestMapList) {
                Optional<LeaveRequest> lr = lrd.findById(Integer.parseInt(requestMap.get("id")));
                if (lr.isPresent()) {
					LeaveRequest ets = new LeaveRequest();
					ets.setApprovedBy(lr.get().getApprovedBy());
					ets.setUser(lr.get().getUser());
					ets.setRequestFromDate(lr.get().getRequestFromDate());
					ets.setRequestToDate(lr.get().getRequestToDate());
					ets.setNoofdays(lr.get().getNoofdays());
					ets.setStatus(requestMap.get("status"));
					ets.setComment(requestMap.get("comment"));
					ets.setApprover(requestMap.get("approver"));
					ets.setId(Integer.parseInt(requestMap.get("id")));
					ets.setReason(requestMap.get("reason"));
					ets.setUsername(requestMap.get("username"));
					lrd.save(ets);
					if ("approved".equalsIgnoreCase(requestMap.get("status"))) {
	                JSONObject jsonObject = new JSONObject();
	                jsonObject.put("user", ets.getUser());
	                jsonObject.put("noofdays", lr.get().getNoofdays());
	                jsonObject.put("status", requestMap.get("status"));
	                jsonArray.put(jsonObject);
                    }
                }
            }

			
        // Combine entries with the same user value
        Map<Integer, JSONObject> uniqueUsersMap = new HashMap<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            int user = jsonObject.getInt("user");
            if (uniqueUsersMap.containsKey(user)) {
                JSONObject existingObject = uniqueUsersMap.get(user);
                int existingNoOfDays = existingObject.getInt("noofdays");
                existingObject.put("noofdays", existingNoOfDays + jsonObject.getInt("noofdays"));
            } else {
                uniqueUsersMap.put(user, jsonObject);
            }
        }

        // Create a new unique array
        JSONArray uniqueJsonArray = new JSONArray();
        for (JSONObject jsonObject : uniqueUsersMap.values()) {
            uniqueJsonArray.put(jsonObject);
        }
		
           for (int i = 0; i < uniqueJsonArray.length(); i++) {
            JSONObject jsonObject = uniqueJsonArray.getJSONObject(i);
            int user = jsonObject.getInt("user");
            int noofdays = jsonObject.getInt("noofdays");
            ls.leaveBalanceChange(user, noofdays, "minus");
        }
		return HrmsUtils.getResponeEntity("Successfully Leaves Request Updated.", HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace(); 
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@Override
	public ResponseEntity<List<LeaveRequest>> getApplyedLeaveInfo(Integer id) {
		List<LeaveRequest> list = new ArrayList<LeaveRequest>();
		try {
			return new ResponseEntity<List<LeaveRequest>>(lrd.findApplyedLeaveInfo(id), HttpStatus.OK);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<LeaveRequest>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> leaverequestcancel(Integer leaverequestid) {
		try {

			Optional<LeaveRequest> lr = lrd.findById(leaverequestid);
			if (lr.isPresent()) {
			lrd.deleteById(leaverequestid);
			return HrmsUtils.getResponeEntity("Successfully Cancel Leave Request.", HttpStatus.OK);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<LeaveRequest>> leaverequestneedtoapprove(Integer id) {
		List<LeaveRequest> list = new ArrayList<LeaveRequest>();
		try {
			return new ResponseEntity<List<LeaveRequest>>(lrd.leaverequestneedtoapprove(id), HttpStatus.OK);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<LeaveRequest>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<String>> getApplyedLeavesList(Integer userId, String fromdate, String todate) {
		List<String> list = new ArrayList<String>();
		try {

            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date fromdateinput = inputFormat.parse(fromdate);
            String fromdateoutput = outputFormat.format(fromdateinput);
            Date todateinput = inputFormat.parse(todate);
            String todateoutput = outputFormat.format(todateinput);
			list = lrd.findLeaveDates(userId, fromdateoutput , todateoutput); 
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			list.sort(Comparator.comparing(date -> LocalDate.parse(date, formatter)));
			return new ResponseEntity<List<String>>(list, HttpStatus.OK);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<String>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
	} 

}
