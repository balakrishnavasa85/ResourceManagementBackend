package com.application.hrms.service.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.application.hrms.JWT.JwtFilter;
import com.application.hrms.POJO.Recrutment;
import com.application.hrms.POJO.RecrutmentAssigners;
import com.application.hrms.POJO.User;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.dao.RecrutmentAssignersDao;
import com.application.hrms.dao.RecrutmentDao;
import com.application.hrms.dao.UserDao;
import com.application.hrms.service.RecrutmentAssignersService;
import com.application.hrms.utils.HrmsUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecrutmentAssignersServiceImpl implements RecrutmentAssignersService {

	@Autowired
	RecrutmentAssignersDao recrutmentAssignersDao;

	@Autowired
	UserDao userDao;

	@Autowired
	JwtFilter jwtFilter;

	@Autowired
	RecrutmentDao recrutmentDao;

	@Override
	public ResponseEntity<String> createrecrtmentassign(Map<String, String> requestMap) {
		try {
			Integer reqid = Integer.parseInt(requestMap.get("id"));
			Optional<RecrutmentAssigners> rinfo = recrutmentAssignersDao.findByReqId(reqid);
//			if (jwtFilter.isAdmin()) {
				if (!rinfo.isPresent()) {
					List<RecrutmentAssigners> list = new ArrayList<RecrutmentAssigners>();
					String usersString = requestMap.get("users"); // Example: "1,2,3,4,5"
					int[] usersArray = Arrays.stream(usersString.split(",")).mapToInt(Integer::parseInt).toArray();
					for (Integer user : usersArray) {
						User uservalue = userDao.getUserDetailById(user);
						Recrutment recrutment = recrutmentDao.findByReqId(reqid);
						if (uservalue != null && recrutment != null) {
							RecrutmentAssigners rdata = new RecrutmentAssigners();
							rdata.setUser(uservalue);
							rdata.setRecrutment(recrutment);
							list.add(rdata);
						}
					}
					if (list.size() > 0) {
						recrutmentAssignersDao.saveAll(list);
						return HrmsUtils.getResponeEntity("Recrutment Assigners Created.", HttpStatus.OK);
					} else {

						return HrmsUtils.getResponeEntity("Recrutment Assigners  Not found.", HttpStatus.NO_CONTENT);
					}

				} else {
					return HrmsUtils.getResponeEntity("Recrutment exits.", HttpStatus.BAD_REQUEST);
				}
//			} else {
//				return HrmsUtils.getResponeEntity(HrmsConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
//			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Recrutment>> getById(Integer id) {
		List<Recrutment> list = new ArrayList<Recrutment>();
		try {
			return new ResponseEntity<List<Recrutment>>(recrutmentDao.getByIdAssigner(id), HttpStatus.OK);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<Recrutment>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
