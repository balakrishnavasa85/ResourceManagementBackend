package com.application.hrms.service.serviceImpl;

import com.application.hrms.JWT.JwtFilter;
import com.application.hrms.JWT.jwtUtil;
import com.application.hrms.POJO.EmpEducation;
import com.application.hrms.POJO.User;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.dao.EmpEducationDao;
import com.application.hrms.dao.UserDao;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.application.hrms.service.EmpEducationService;
import com.application.hrms.utils.HrmsUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmpEducationServiceImpl implements EmpEducationService {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	com.application.hrms.JWT.jwtUtil jwtUtil;

	@Autowired
	JwtFilter jwtFilter;

	@Autowired
	EmpEducationDao relationDao;

	@Autowired
	UserDao userdao;

	@Override
	public ResponseEntity<List<EmpEducation>> getEmpEducationInfo(Integer id) {
		List<EmpEducation> list = new ArrayList<EmpEducation>();
		try {
//			if (jwtFilter.isAdmin()) {
			Optional<EmpEducation> optional = relationDao.findById(id);
			if (optional.isPresent()) {
				return new ResponseEntity<List<EmpEducation>>(relationDao.getEmpEducationById(id), HttpStatus.OK);
			} else {
				return new ResponseEntity<List<EmpEducation>>(list, HttpStatus.UNAUTHORIZED);
			}
//			} else {
//				return new ResponseEntity<List<EmpEducationWrapper>>(list, HttpStatus.UNAUTHORIZED);
//			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<EmpEducation>>(list, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@Override
	public ResponseEntity<String> updateInfo(Integer id, Map<String, String> requestMap) {
		try {
//			if (jwtFilter.isAdmin()) {

			Optional<User> useri = userdao.findById(id);
			Optional<EmpEducation> optional = relationDao.findById(Integer.parseInt(requestMap.get("user")));
			if (optional.isPresent()) {
				EmpEducation relation = new EmpEducation();
				relation.setStudy(requestMap.get("study"));
				relation.setDuration(requestMap.get("duration"));
				relation.setJoiningDate(requestMap.get("joiningdate"));
				relation.setReleaveDate(requestMap.get("releavedate"));
				relation.setUniversity(requestMap.get("university"));
				relation.setUser(useri.get());
				relation.setFilepath(requestMap.get("filepath"));
				relation.setId(id);
				relationDao.save(relation);
				return HrmsUtils.getResponeEntity("Relation Info is updated Successfully", HttpStatus.OK);

			} else {
				return HrmsUtils.getResponeEntity("Relation id doesn't exist", HttpStatus.OK);
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
	public ResponseEntity<String> create(Integer id, List<String> educations, List<MultipartFile> files) {
		try {
			Optional<User> useri = userdao.findById(id);
			if (useri.isPresent()) {
				for (int i = 0; i < educations.size(); i++) {
					String education = educations.get(i);  
					MultipartFile file = files.get(i); 

//					education = education.replaceAll("[{}\"]", "");  
					education = education.substring(1, education.length() - 1).replaceAll("[{}\"]", "");

					Map<String, String> map = Arrays.stream(education.split(",")).map(entry -> entry.split(":"))
							.collect(Collectors.toMap(e -> e[0].trim(), e -> e.length > 1 ? e[1].trim() : ""));

					String study = map.get("study");
					String durationStr = map.get("duration");
					String joiningDate = map.get("joiningDate");
					String relievedDate = map.get("relievedDate");
					String university = map.get("university");

					EmpEducation empEducation = new EmpEducation();
					empEducation.setStudy(study);
					empEducation.setDuration(durationStr);
					empEducation.setJoiningDate(joiningDate);
					empEducation.setReleaveDate(relievedDate);
					empEducation.setUniversity(university);
					empEducation.setUser(useri.get());
					final String UPLOAD_DIR = "uploads/education/" + id + "/"; 
					String fileName = id + "_" + files.get(i).getOriginalFilename();
					Path filePath = Paths.get(UPLOAD_DIR + fileName);
					Files.createDirectories(filePath.getParent());
					Files.write(filePath, file.getBytes());
					String filepath = filePath.toString();
					empEducation.setFilepath(filepath);
					relationDao.save(empEducation);
				}
				return HrmsUtils.getResponeEntity("Successfully  Created.", HttpStatus.OK);
			} else {
				return HrmsUtils.getResponeEntity("User Not found.", HttpStatus.NO_CONTENT);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private EmpEducation getEmpEducationFromMap(Map<String, String> requestMap) {

		Optional<User> useri = userdao.findById(Integer.parseInt(requestMap.get("user")));
		EmpEducation relation = new EmpEducation();
		relation.setStudy(requestMap.get("study"));
		relation.setDuration(requestMap.get("duration"));
		relation.setJoiningDate(requestMap.get("joiningdate"));
		relation.setReleaveDate(requestMap.get("releavedate"));
		relation.setUniversity(requestMap.get("university"));
		relation.setUser(useri.get());
		relation.setFilepath(requestMap.get("filepath"));
		return relation;
	}

	@Override
	public ResponseEntity<List<EmpEducation>> getUserEmpEducationInfo(Integer id) {
		List<EmpEducation> list = new ArrayList<EmpEducation>();
		try {
//			if (jwtFilter.isAdmin()) {
			List<EmpEducation> optional = relationDao.getEmpEducationByUserId(id);
			if (optional.size() > 0) {
				return new ResponseEntity<List<EmpEducation>>(optional, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<EmpEducation>>(list, HttpStatus.OK);
			}
//			} else {
//				return new ResponseEntity<List<EmpEducationWrapper>>(list, HttpStatus.UNAUTHORIZED);
//			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<EmpEducation>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
