package com.application.hrms.service.serviceImpl;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.application.hrms.POJO.UserWorkingDays;
import com.application.hrms.dao.UserWorkingDaysDao;
import com.application.hrms.service.UserWorkingDaysService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserWorkingDaysServiceImpl implements UserWorkingDaysService {

	@Autowired
	UserWorkingDaysDao userWorkingDaysDao;

	@Override
	public ResponseEntity<List<UserWorkingDays>> getPayslip(Integer id, Integer month, Integer year) {
		List<UserWorkingDays> list = new ArrayList<UserWorkingDays>();
		try {
			String monthname = Month.of(month).getDisplayName(TextStyle.FULL, Locale.ENGLISH);
			List<UserWorkingDays> lists = userWorkingDaysDao.getPayslip(id, monthname, year);
			return new ResponseEntity<List<UserWorkingDays>>(lists, HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<UserWorkingDays>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<UserWorkingDays>> getPayslipById(Integer id) {
		List<UserWorkingDays> list = new ArrayList<UserWorkingDays>();
		try {
			List<UserWorkingDays> lists = userWorkingDaysDao.getPayslipById(id);
			return new ResponseEntity<List<UserWorkingDays>>(lists, HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<UserWorkingDays>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<UserWorkingDays>> getAllUserSalaryInfo(Integer month, Integer year) {
		List<UserWorkingDays> list = new ArrayList<UserWorkingDays>();
		try {
			String monthname = Month.of(month).getDisplayName(TextStyle.FULL, Locale.ENGLISH);
			List<UserWorkingDays> lists = userWorkingDaysDao.getAllUserSalaryInfo(monthname, year);
			return new ResponseEntity<List<UserWorkingDays>>(lists, HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<UserWorkingDays>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}