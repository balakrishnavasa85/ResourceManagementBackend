package com.application.hrms.rest.restImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import com.application.hrms.POJO.Tds;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.rest.TdsRest;
import com.application.hrms.service.TdsService;
import com.application.hrms.utils.HrmsUtils;

@RestController
public class TdsRestImpl implements TdsRest {

	@Autowired
	TdsService tdss;

	@Override
	public ResponseEntity<String> create(List<Map<String, String>> requestMap) {
		try {
			return tdss.create(requestMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Tds>> getAll() {
		try {
			return tdss.getAll();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<Tds>>((MultiValueMap<String, String>) new ArrayList<Object>(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> delete() {
		try {
			return tdss.delete();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<Map> getByAmount(Integer amount,Integer groupid) {
		try {
			return tdss.getByAmount(amount,groupid);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<Map>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
