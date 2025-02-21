package com.application.hrms.service.serviceImpl;

import com.application.hrms.JWT.JwtFilter;
import com.application.hrms.JWT.jwtUtil;
import com.application.hrms.POJO.Department;
import com.application.hrms.POJO.IdentityDetails;
import com.application.hrms.POJO.SalaryDetails;
import com.application.hrms.POJO.User;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.dao.DepartmentDao;
import com.application.hrms.dao.IdentityDetailsDao;
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
import com.application.hrms.service.IdentityDetailsService;
import com.application.hrms.service.SalaryDetailsService;
import com.application.hrms.utils.HrmsUtils;
import com.application.hrms.wrapper.DepartmentWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IdentityDetailsServiceImpl implements IdentityDetailsService {
	@Autowired
	IdentityDetailsDao idd;

	@Autowired
	UserDao userDao;

	@Override
	public ResponseEntity<String> createIdentity(Integer user, Map<String, String> requestMap) {
		Optional<User> optional = userDao.findById(user);
		if (optional.isPresent()) {
			Optional<IdentityDetails> sd = idd.findByUser(user);
			IdentityDetails sdData = new IdentityDetails();
			if (sd.isPresent()) {
				sdData.setId(sd.get().getId());
				sdData.setPan(requestMap.containsKey("pan") ? requestMap.get("pan") : sd.get().getPan());
				sdData.setAadhar(requestMap.containsKey("aadhar") ? requestMap.get("aadhar") : sd.get().getAadhar());
				sdData.setPassport(requestMap.containsKey("passport") ? requestMap.get("passport")
						:  sd.get().getPassport());
				sdData.setExpiry(
						requestMap.containsKey("expiry") ? requestMap.get("expiry") : sd.get().getExpiry());
			} else {
				sdData.setPan(requestMap.containsKey("pan") ? requestMap.get("pan") : "");
				sdData.setAadhar(requestMap.containsKey("aadhar") ? requestMap.get("aadhar") : "");
				sdData.setPassport(requestMap.containsKey("passport") ? requestMap.get("passport")
						:  "");
				sdData.setExpiry(
						requestMap.containsKey("expiry") ? requestMap.get("ifsccode") : "");
			}
			sdData.setUser(user);
			idd.save(sdData);
			return HrmsUtils.getResponeEntity("Identiy Details uploaded successfully", HttpStatus.OK);
		} else {
			return HrmsUtils.getResponeEntity("User not found", HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public ResponseEntity<IdentityDetails> getDetailsIdentity(Integer user) {
		 
			Optional<IdentityDetails> sd = idd.findByUser(user);
			if(sd.isPresent())
			{
				return new ResponseEntity<IdentityDetails>(sd.get(), HttpStatus.OK);
			}
			else {
				return new ResponseEntity<IdentityDetails>( HttpStatus.NO_CONTENT);
			}
	}
}
