package com.application.hrms.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.application.hrms.POJO.Tds;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.dao.TdsDao;
import com.application.hrms.service.TdsService;
import com.application.hrms.utils.HrmsUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TdsServiceImpl implements TdsService {

	@Autowired
	TdsDao tdsDao;

	@Override
	public ResponseEntity<String> create(List<Map<String, String>> requestMap) {
		try {
			List<Tds> tdsList = new ArrayList<>();
			for (int i = 0; i < requestMap.size(); i++) {
				Tds tdinfo = new Tds();
				tdinfo.setFromamount(Integer.parseInt(requestMap.get(i).get("fromAmount")));
				tdinfo.setToamount(Integer.parseInt(requestMap.get(i).get("toAmount")));
				tdinfo.setPercentage(Integer.parseInt(requestMap.get(i).get("percentage")));
				tdinfo.setName(requestMap.get(i).get("name"));
				tdsList.add(tdinfo);
			}
			if (tdsList.size() > 0) {
				tdsDao.saveAll(tdsList);
				return HrmsUtils.getResponeEntity("Successfully  Created.", HttpStatus.OK);
			} else {

				return HrmsUtils.getResponeEntity("User Not found.", HttpStatus.NO_CONTENT);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Tds>> getAll() {
		List<Tds> list = new ArrayList<Tds>();
		try {
			return new ResponseEntity<List<Tds>>(tdsDao.findAll(), HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<Tds>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> delete() {
		try {
			 tdsDao.deleteAll();
				return HrmsUtils.getResponeEntity("Information Deleted.", HttpStatus.OK);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
