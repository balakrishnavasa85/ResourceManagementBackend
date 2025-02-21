package com.application.hrms.service.serviceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.application.hrms.POJO.Leaves;
import com.application.hrms.POJO.Relation;
import com.application.hrms.POJO.User;
import com.application.hrms.dao.LeavesDao;
import com.application.hrms.dao.UserDao;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.service.LeavesService;
import com.application.hrms.utils.HrmsUtils;
import com.application.hrms.wrapper.DepartmentWrapper;

import antlr.collections.List;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LeavesServiceImpl implements LeavesService {

	@Autowired
	UserDao userdao;

	@Autowired
	LeavesDao leavesdao; 

	@Override
	@Transactional
	public ResponseEntity<String> leaveBalanceChange(Integer id, Integer count , String requestMap) {
		try {
			Optional<User> useri = userdao.findById(id);
			Leaves leavesi = leavesdao.getLeavesByUserId(id);
			if (useri.isPresent() ) {
				Integer countValue = null ;
				if(requestMap.equals("add"))
				{
					countValue = leavesi.getBalance() + count;
				}
				else if(requestMap.equals("minus") && leavesi.getBalance() > 0)
				{
					countValue = leavesi.getBalance() - count;
				}
				else					
				{
					return HrmsUtils.getResponeEntity("Leave Request Not Submited", HttpStatus.BAD_REQUEST);
				}
				leavesdao.updateLeaves(countValue, leavesi.getId());	
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
	public ResponseEntity<String> createleaveBalance(Integer id) {
		try {
			Optional<User> useri = userdao.findById(id);
			Optional<Leaves> leavesi = leavesdao.findById(id);
			if (useri.isPresent() && !leavesi.isPresent()) {
				Leaves ls = new Leaves();
				ls.setBalance(2);
				ls.setUser(id);
				leavesdao.save(ls);
				return HrmsUtils.getResponeEntity("Successfully Leave Balance Submited.", HttpStatus.OK);
			} else {
				return HrmsUtils.getResponeEntity("Leave Request Not Submited", HttpStatus.BAD_REQUEST);
			}
		}

		 catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

	}  

	@Override
	public ResponseEntity<Leaves> getLeavesInfo(Integer id) {
		Leaves leaves = leavesdao.getLeavesByUserId(id);
		try {
				return new ResponseEntity<Leaves>(leavesdao.getLeavesByUserId(id), HttpStatus.OK);
			 

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<Leaves>(HttpStatus.INTERNAL_SERVER_ERROR);
	} 
}
