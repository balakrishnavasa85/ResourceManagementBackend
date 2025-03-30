package com.application.hrms.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.application.hrms.POJO.UserProcess;
import com.application.hrms.dao.UserProcessDao;
import com.application.hrms.service.UserProcessService;
import com.application.hrms.wrapper.UserProcessWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserProcessServiceImpl implements UserProcessService {

	@Autowired
	UserProcessDao userProcessDao;

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
	public ResponseEntity<List<UserProcessWrapper>> getList() {
		List<UserProcessWrapper> up = new ArrayList<UserProcessWrapper>();
//		try {
//			return new ResponseEntity<List<UserProcessWrapper>>(userProcessDao.getRecruitmentUserProcess(),
//					HttpStatus.OK);
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}

		return new ResponseEntity<List<UserProcessWrapper>>(up, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
