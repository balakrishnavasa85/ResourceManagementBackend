package com.application.hrms.service.serviceImpl;

import com.application.hrms.JWT.JwtFilter;
import com.application.hrms.JWT.jwtUtil;
import com.application.hrms.POJO.EmpEducation;
import com.application.hrms.POJO.EmpExperiance;
import com.application.hrms.POJO.User;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.dao.EmpExperianceDao;
import com.application.hrms.dao.UserDao;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.application.hrms.service.EmpExperianceService;
import com.application.hrms.utils.HrmsUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmpExperianceServiceImpl implements EmpExperianceService {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	com.application.hrms.JWT.jwtUtil jwtUtil;

	@Autowired
	JwtFilter jwtFilter;

	@Autowired
	EmpExperianceDao relationDao;

	@Autowired
	UserDao userdao;

	@Override
	public ResponseEntity<List<EmpExperiance>> getEmpExperianceInfo(Integer id) {
		List<EmpExperiance> list = new ArrayList<EmpExperiance>();
		try {
//            if (jwtFilter.isAdmin()) {
			Optional<EmpExperiance> optional = relationDao.findById(id);
			if (optional.isPresent()) {
				return new ResponseEntity<List<EmpExperiance>>(relationDao.getEmpExperianceById(id), HttpStatus.OK);
			} else {
				return new ResponseEntity<List<EmpExperiance>>(list, HttpStatus.UNAUTHORIZED);
			}
//            } else {
//                return new ResponseEntity<List<EmpExperianceWrapper>>(list, HttpStatus.UNAUTHORIZED);
//            }

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<EmpExperiance>>(list, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@Override
	public ResponseEntity<String> updateInfo(Integer id, Map<String, String> requestMap) {
		try {

			Optional<User> useri = userdao.findById(id);
			Optional<EmpExperiance> optional = relationDao.findById(id);
			if (optional.isPresent()) {
				EmpExperiance relation = new EmpExperiance();
				relation.setCompanyName(requestMap.get("companyname"));
				relation.setExperiance(requestMap.get("experiance"));
				relation.setJoiningDate(requestMap.get("joiningdate"));
				relation.setReleaveDate(requestMap.get("releavedate"));
				relation.setMode(requestMap.get("mode"));
				relation.setUser(useri.get());
				relation.setId(id);
				relation.setFilepath(requestMap.get("filepath"));
				relationDao.save(relation);
				return HrmsUtils.getResponeEntity("Relation Info is updated Successfully", HttpStatus.OK);

			} else {
				return HrmsUtils.getResponeEntity("Relation id doesn't exist", HttpStatus.OK);
			}
//	            } else {
//	                return HrmsUtils.getResponeEntity(HrmsConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
//	            }
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> create(Integer id, List<String> experiances, List<MultipartFile> files) {
		try {
			Optional<User> useri = userdao.findById(id);
			if (useri.isPresent()) {
				String companynamenew = null;
				String experianceStrnew = null;
				String joiningDatenew = null;
				String relievedDatenew = null;
				String modenew = null;
				List<String> presentKeys = new ArrayList<>();
				for (int i = 0; i < experiances.size(); i++) {
					String experiance = experiances.get(i);

					List<String> requiredKeys = Arrays.asList("companyname", "experiance", "joiningDate",
							"relievedDate", "mode");

					experiance = experiance.substring(1, experiance.length() - 1).replaceAll("[{}\"]", "");

					Map<String, String> map = Arrays.stream(experiance.split(",")).map(entry -> entry.split(":"))
							.collect(Collectors.toMap(e -> e[0].trim(), e -> e.length > 1 ? e[1].trim() : ""));

					if (map.keySet().containsAll(requiredKeys)) {
						MultipartFile file = files.get(i);
						System.out.println("Map contains all required values.");
						String companyname = map.get("companyname");
						String experianceStr = map.get("experiance");
						String joiningDate = map.get("joiningDate");
						String relievedDate = map.get("relievedDate");
						String mode = map.get("mode");

						EmpExperiance empexperiance = new EmpExperiance();
						empexperiance.setCompanyName(companyname);
						empexperiance.setExperiance(experianceStr);
						empexperiance.setJoiningDate(joiningDate);
						empexperiance.setReleaveDate(relievedDate);
						empexperiance.setMode(mode);
						empexperiance.setUser(useri.get());
						final String UPLOAD_DIR = "uploads/education/" + id + "/";
						String fileName = id + "_" + files.get(i).getOriginalFilename();
						Path filePath = Paths.get(UPLOAD_DIR + fileName);
						Files.createDirectories(filePath.getParent());
						Files.write(filePath, file.getBytes());
						String filepath = filePath.toString();
						empexperiance.setFilepath(filepath);
						relationDao.save(empexperiance);
					} else {
						for (String key : requiredKeys) {
							if (map.containsKey(key)) {
								presentKeys.add(key);
								if (key.equals("companyname")) {
									companynamenew = map.get(key);
									break;
								} else if (key.equals("experiance")) {
									experianceStrnew = map.get(key);
									break;
								} else if (key.equals("joiningDate")) {
									joiningDatenew = map.get(key);
									break;
								} else if (key.equals("relievedDate")) {
									relievedDatenew = map.get(key);
									break;
								} else if (key.equals("mode")) {
									modenew = map.get(key);
									break;
								}
							}
						}

						if (companynamenew != null && experianceStrnew != null && joiningDatenew != null
								&& relievedDatenew != null && modenew != null) {
							MultipartFile file = files.get(0);
							EmpExperiance empexperiance = new EmpExperiance();
							empexperiance.setCompanyName(companynamenew);
							empexperiance.setExperiance(experianceStrnew);
							empexperiance.setJoiningDate(joiningDatenew);
							empexperiance.setReleaveDate(relievedDatenew);
							empexperiance.setMode(modenew);
							empexperiance.setUser(useri.get());
							final String UPLOAD_DIR = "uploads/experiance/" + id + "/";
							String fileName = id + "_" + files.get(0).getOriginalFilename();
							Path filePath = Paths.get(UPLOAD_DIR + fileName);
							Files.createDirectories(filePath.getParent());
							Files.write(filePath, file.getBytes());
							String filepath = filePath.toString();
							empexperiance.setFilepath(filepath);
							relationDao.save(empexperiance);
						}
					}

				}
				return HrmsUtils.getResponeEntity("Successfully  Created.", HttpStatus.OK);
			} else {
				return HrmsUtils.getResponeEntity("User Not found.", HttpStatus.NO_CONTENT);
			}

		} catch (

		Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private EmpExperiance getEmpExperianceFromMap(Map<String, String> requestMap) {

		Optional<User> useri = userdao.findById(Integer.parseInt(requestMap.get("user")));
		EmpExperiance relation = new EmpExperiance();
		relation.setCompanyName(requestMap.get("companyname"));
		relation.setExperiance(requestMap.get("experiance"));
		relation.setJoiningDate(requestMap.get("joiningdate"));
		relation.setReleaveDate(requestMap.get("releavedate"));
		relation.setMode(requestMap.get("mode"));
		relation.setFilepath(requestMap.get("filepath"));
		relation.setUser(useri.get());
		return relation;
	}

	@Override
	public ResponseEntity<List<EmpExperiance>> getUserEmpExperianceInfo(Integer id) {
		List<EmpExperiance> list = new ArrayList<EmpExperiance>();
		try {
//        if (jwtFilter.isAdmin()) {
			List<EmpExperiance> optional = relationDao.getEmpExperianceByUserId(id);
			if (optional.size() > 0) {
				return new ResponseEntity<List<EmpExperiance>>(optional, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<EmpExperiance>>(list, HttpStatus.OK);
			}
//        } else {
//            return new ResponseEntity<List<EmpExperianceWrapper>>(list, HttpStatus.UNAUTHORIZED);
//        }

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<EmpExperiance>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
