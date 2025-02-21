package com.application.hrms.service.serviceImpl;

import com.application.hrms.JWT.JwtFilter;
import com.application.hrms.JWT.jwtUtil;
import com.application.hrms.POJO.Department;
import com.application.hrms.POJO.SalaryDetails;
import com.application.hrms.POJO.User;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.dao.DepartmentDao;
import com.application.hrms.dao.SalaryDetailsDao;
import com.application.hrms.dao.UserDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import com.application.hrms.service.DepartmentService;
import com.application.hrms.service.SalaryDetailsService;
import com.application.hrms.utils.HrmsUtils;
import com.application.hrms.wrapper.DepartmentWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SalaryDetailsServiceImpl implements SalaryDetailsService {
	@Autowired
	SalaryDetailsDao sdd;

	@Autowired
	UserDao userDao;

	@Override
	public ResponseEntity<String> create(Integer user, Map<String, String> requestMap) {
		Optional<User> optional = userDao.findById(user);
		if (optional.isPresent()) {
			Optional<SalaryDetails> sd = sdd.findByUser(user);
			SalaryDetails sdData = new SalaryDetails();
			if (sd.isPresent()) {
				sdData.setId(sd.get().getId());
				sdData.setUan(requestMap.containsKey("uan") ? requestMap.get("uan") : sd.get().getUan());
				sdData.setPf(requestMap.containsKey("pf") ? requestMap.get("pf") : sd.get().getPf());
				sdData.setBankname(requestMap.containsKey("bankname") ? requestMap.get("bankname") : sd.get().getBankname());
				sdData.setAccountnumber(requestMap.containsKey("accountnumber") ? requestMap.get("accountnumber")
						:  sd.get().getAccountnumber());
				sdData.setIfsccode(
						requestMap.containsKey("ifsccode") ? requestMap.get("ifsccode") : sd.get().getIfsccode());
			} else {
				sdData.setUan(requestMap.containsKey("uan") ? requestMap.get("uan") : "");
				sdData.setPf(requestMap.containsKey("pf") ? requestMap.get("pf") : "");
				sdData.setBankname(requestMap.containsKey("bankname") ? requestMap.get("bankname") : "");
				sdData.setAccountnumber(requestMap.containsKey("accountnumber") ? requestMap.get("accountnumber")
						:  "");
				sdData.setIfsccode(
						requestMap.containsKey("ifsccode") ? requestMap.get("ifsccode") : "");
			}
			sdData.setUser(user);
			sdd.save(sdData);
			return HrmsUtils.getResponeEntity("Profile picture uploaded successfully", HttpStatus.OK);
		} else {
			return HrmsUtils.getResponeEntity("User not found", HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public ResponseEntity<SalaryDetails> getDetails(Integer user) {
		 
			Optional<SalaryDetails> sd = sdd.findByUser(user);
			if(sd.isPresent())
			{
				return new ResponseEntity<SalaryDetails>(sd.get(), HttpStatus.OK);
			}
			else {
				return new ResponseEntity<SalaryDetails>( HttpStatus.NO_CONTENT);
			}
//			return null;
		
		// TODO Auto-generated method stub
	}
}
