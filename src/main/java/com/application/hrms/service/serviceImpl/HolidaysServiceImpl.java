package com.application.hrms.service.serviceImpl;

import com.google.common.base.Strings;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.application.hrms.JWT.CustomerUserDetailsService;
import com.application.hrms.JWT.JwtFilter;
import com.application.hrms.JWT.jwtUtil;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.dao.HolidaysDao;
import com.application.hrms.POJO.Holidays;
import com.application.hrms.service.HolidaysService;
import com.application.hrms.utils.HrmsUtils;

import lombok.extern.slf4j.Slf4j;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Service
public class HolidaysServiceImpl implements HolidaysService {

	@Autowired
	HolidaysDao hsDao;

	public ResponseEntity<List<Holidays>> getAll() {
		List<Holidays> list = new ArrayList<Holidays>();
		try {
				return new ResponseEntity<List<Holidays>>(hsDao.findAll(), HttpStatus.OK);
			

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<Holidays>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Holidays>> getByMonthName(Integer month) {

		List<Holidays> list = new ArrayList<Holidays>();
		try {
			List<Holidays> holidays = hsDao.findAll();
		  for (Holidays holiday : holidays) {
            LocalDate date = LocalDate.parse(holiday.getDate());  
            if (date.getMonthValue() == month) {
                list.add(holiday);
            }
        }
        return new ResponseEntity<List<Holidays>>(list, HttpStatus.OK);

	} catch (Exception ex) {
		ex.printStackTrace();
	}
	return new ResponseEntity<List<Holidays>>(list, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@Override
	public ResponseEntity<List<Holidays>> getHolidayList(String fromdate, String todate) {
		 List<Holidays> list = new ArrayList<Holidays>();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fromDate = LocalDate.parse(fromdate, formatter);
            LocalDate toDate = LocalDate.parse(todate, formatter);

            List<Holidays> holidays = hsDao.findAll();
            for (Holidays holiday : holidays) {
                LocalDate date = LocalDate.parse(holiday.getDate(), formatter);
                if ((date.isEqual(fromDate) || date.isAfter(fromDate)) && (date.isEqual(toDate) || date.isBefore(toDate))) {
                    list.add(holiday);
                }
            }
            return new ResponseEntity<List<Holidays>>(list, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<Holidays>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
   } 
}