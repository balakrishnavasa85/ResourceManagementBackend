package com.application.hrms.service.serviceImpl;

import com.application.hrms.JWT.JwtFilter;
import com.application.hrms.JWT.jwtUtil;
import com.application.hrms.POJO.Relation;
import com.application.hrms.POJO.User;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.dao.RelationDao;
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

import com.application.hrms.service.RelationService;
import com.application.hrms.utils.HrmsUtils;
import com.application.hrms.wrapper.RelationWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RelationServiceImpl implements RelationService {

	@Autowired
	RelationDao relationDao;

	@Autowired
	UserDao userDao;

	@Override
	public ResponseEntity<String> create(Integer id, List<Map<String, String>> requestMap) {
		try {
			System.out.println("load");

			Optional<User> useri = userDao.findById(id);
			List<Relation> relationList = new ArrayList<>();
			if (useri.isPresent()) {
				for (int i = 0; i < requestMap.size(); i++) {
					Relation rel = new Relation();
					rel.setAge(Integer.parseInt(requestMap.get(i).get("age")));
					rel.setDob(requestMap.get(i).get("dob"));
					rel.setName(requestMap.get(i).get("name"));
					rel.setRelation(requestMap.get(i).get("relation"));
					rel.setStatus("y");
					rel.setUser(useri.get());
					relationList.add(rel);
				}
				if (relationList.size() > 0) {
					relationDao.saveAll(relationList);

					return HrmsUtils.getResponeEntity("Successfully  Created.", HttpStatus.OK);
				} else {

					return HrmsUtils.getResponeEntity("User Not found.", HttpStatus.NO_CONTENT);
				}

			} else {

				return HrmsUtils.getResponeEntity("User Not found.", HttpStatus.NO_CONTENT);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	} 

	@Override
	public ResponseEntity<List<RelationWrapper>> getUserRelationInfo(Integer id) {
		List<RelationWrapper> list = new ArrayList<RelationWrapper>();
		try {
			List<RelationWrapper> optional = relationDao.getRelationByUserId(id);
			if (optional.size() > 0) {
				return new ResponseEntity<List<RelationWrapper>>(optional, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<RelationWrapper>>(list, HttpStatus.OK);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<RelationWrapper>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
