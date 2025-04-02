package com.application.hrms.service.serviceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.application.hrms.POJO.UserProcess;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.dao.UserDao;
import com.application.hrms.dao.UserProcessDao;
import com.application.hrms.service.UserProcessService;
import com.application.hrms.utils.HrmsUtils;
import com.application.hrms.wrapper.UserProcessWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserProcessServiceImpl implements UserProcessService {

	@Autowired
	UserProcessDao userProcessDao;
	
	@Autowired
	UserDao userDao;

	@Override
	public ResponseEntity<List<UserProcess>> check(Map<String, String> requestMap) {
		List<UserProcess> up = new ArrayList<UserProcess>();
		try {
			String aadhar = requestMap.get("aadhar");
			String pan = requestMap.get("pan");
			return new ResponseEntity<List<UserProcess>>(userProcessDao.checkValue(aadhar, pan), HttpStatus.OK);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return new ResponseEntity<List<UserProcess>>(up, HttpStatus.INTERNAL_SERVER_ERROR);
	}
 
	@Override
	public ResponseEntity<String> updateUserConformation(Map<String, String> requestMap) {
		try {
			Optional<UserProcess> up = userProcessDao.findById(Integer.parseInt(requestMap.get("userProcessId")));
			if(up.isPresent()) {
				LocalDate currentDate = LocalDate.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				
				Integer opuserid = Integer.parseInt(requestMap.get("opesuserid"));
				String opusername = userDao.getUserName(Integer.parseInt(requestMap.get("opesuserid")));
				String opusercomment = requestMap.get("comment");
				String preferedjoingdate = requestMap.get("preferedJoiningDate");
				String userconformation = requestMap.get("userConformation");
				String operationdate = currentDate.format(formatter);
				Integer id = Integer.parseInt(requestMap.get("userProcessId"));
				
				userProcessDao.updateOperationData(opuserid,opusername,opusercomment,preferedjoingdate,userconformation,operationdate,id);
				return HrmsUtils.getResponeEntity("Successfully  Updated.", HttpStatus.OK);
			}
			else
			{
				return HrmsUtils.getResponeEntity("Opsteam not valid.", HttpStatus.NOT_FOUND);				
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> offerInitiate(Map<String, String> requestMap) {
		try {
			Optional<UserProcess> up = userProcessDao.findById(Integer.parseInt(requestMap.get("userProcessId")));
			if(up.isPresent()) { 
				String offergenerateddate = requestMap.get("offergenerateddate");
				String password = requestMap.get("password");
				String userlink = requestMap.get("userlink");
				Integer id = Integer.parseInt(requestMap.get("userProcessId"));
				
				userProcessDao.offerInitiate(offergenerateddate,password,userlink,id);
				return HrmsUtils.getResponeEntity("Successfully  Updated.", HttpStatus.OK);
			}
			else
			{
				return HrmsUtils.getResponeEntity("Opsteam not valid.", HttpStatus.NOT_FOUND);				
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
