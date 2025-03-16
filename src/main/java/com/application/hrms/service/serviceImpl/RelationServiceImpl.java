package com.application.hrms.service.serviceImpl;

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

import com.application.hrms.JWT.JwtFilter;
import com.application.hrms.POJO.Relation;
import com.application.hrms.POJO.User;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.dao.RelationDao;
import com.application.hrms.dao.UserDao;
import com.application.hrms.service.RelationService;
import com.application.hrms.utils.HrmsUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RelationServiceImpl implements RelationService {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	com.application.hrms.JWT.jwtUtil jwtUtil;

	@Autowired
	JwtFilter jwtFilter;

	@Autowired
	RelationDao relationDao;

	@Autowired
	UserDao userDao;

	@Override
	public ResponseEntity<String> create(Map<String, String> requestMap) {
		try {
			Optional<Relation> Relation = relationDao.findByName(requestMap.get("name"));
			if (Objects.isNull(Relation)) {
				relationDao.save(getRelationFromMap(requestMap));
				return HrmsUtils.getResponeEntity("Successfully  Created.", HttpStatus.OK);
			} else {
				return HrmsUtils.getResponeEntity("Relation already exits.", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private Relation getRelationFromMap(Map<String, String> requestMap) {
		Optional<User> useri = userDao.findById(Integer.parseInt(requestMap.get("user")));
		Relation relation = new Relation();
		relation.setName(requestMap.get("name"));
		relation.setStatus("y");
		relation.setRelation(requestMap.get("relation"));
		relation.setUser(useri.get());
		relation.setAge(Integer.parseInt(requestMap.get(("age"))));
		relation.setDob(requestMap.get("dob"));

		return relation;
	}

	@Override
	public ResponseEntity<List<Relation>> getUserRelationInfo(Integer id) {
		List<Relation> list = new ArrayList<Relation>();
		try {
			List<Relation> optional = relationDao.getRelationByUserId(id);
			if (optional.size() > 0) {
				return new ResponseEntity<List<Relation>>(optional, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<Relation>>(list, HttpStatus.OK);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<Relation>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
