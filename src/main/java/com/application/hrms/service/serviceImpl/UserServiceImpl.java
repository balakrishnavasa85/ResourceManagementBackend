package com.application.hrms.service.serviceImpl;

import com.google.common.base.Strings;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.application.hrms.JWT.CustomerUserDetailsService;
import com.application.hrms.JWT.JwtFilter;
import com.application.hrms.JWT.jwtUtil;
import com.application.hrms.POJO.DeductionGroup;
import com.application.hrms.POJO.Department;
import com.application.hrms.POJO.Designation;
import com.application.hrms.POJO.User;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.dao.DeductionGroupDao;
import com.application.hrms.dao.DepartmentDao;
import com.application.hrms.dao.DesignationDao;
import com.application.hrms.dao.UserDao;
import com.application.hrms.service.LeavesService;
import com.application.hrms.service.UserService;
import com.application.hrms.utils.HrmsUtils;
import com.application.hrms.utils.EmailUtil;
import com.application.hrms.wrapper.UserWrapper;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

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
import com.application.hrms.service.serviceImpl.EmpTImeSheetServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Autowired
	DepartmentDao departmentDao;

	@Autowired
	DesignationDao designationDao;

	@Autowired
	LeavesService ls;

	@Autowired
	DeductionGroupDao dgd;

	@Autowired
	EmpTImeSheetServiceImpl etssi;

	public ResponseEntity<String> signUp(Map<String, String> requestMap) {
		try {
			if (validaSignUpMap(requestMap)) {
				// System.out.println("inside validaSignUpMap");
				User user = userDao.findByEmailId(requestMap.get("email"));
				if (Objects.isNull(user)) {
					userDao.save(getUserFromMap(requestMap));
					User saveduser = userDao.findByEmailId(requestMap.get("email"));
					ls.createleaveBalance(saveduser.getId());
					return HrmsUtils.getResponeEntity("Successfully  Registered.", HttpStatus.OK);
				} else {
					// System.out.println("Email already exits.");
					return HrmsUtils.getResponeEntity("Email already exits.", HttpStatus.BAD_REQUEST);
				}
			} else {
				// System .out.println(HrmsConstants.INVALID_DATA);
				return HrmsUtils.getResponeEntity(HrmsConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// System.out.println(HrmsConstants.SOMETHING_WENT_WRONG);
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private boolean validaSignUpMap(Map<String, String> requestMap) {
		if (requestMap.containsKey("name") && requestMap.containsKey("contact") && requestMap.containsKey("email")) {
			return true;
		}
		return false;
	}

	private User getUserFromMap(Map<String, String> requestMap) throws JSONException {
		User user = new User();
		user.setName(requestMap.get("name"));
		user.setContactNumber(requestMap.get("contact"));
		user.setEmail(requestMap.get("email"));
		user.setPassword("1234");
		user.setStatus("y");
		user.setRole(requestMap.containsKey("role") ? requestMap.get("role") : "admin");
		Department dInfo = departmentDao.getDepartmentInfoById(Integer.parseInt(requestMap.get("department_id")));
		user.setDepartment(dInfo);
		Designation desig = designationDao.getDesignationInfoById(Integer.parseInt(requestMap.get("designation_id")));
		user.setDesignation(desig);
		user.setDob(requestMap.get("dob"));
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		user.setDoj(requestMap.containsKey("doj") ? requestMap.get("doj") : currentDate.format(formatter));
		user.setGender(requestMap.get("gender"));
		user.setDor(requestMap.containsKey("dor") ? requestMap.get("dor") : "");
		user.setSalarypa(requestMap.get("salarypa"));
		user.setMaritalstatus(requestMap.get("maritalstatus"));
		user.setAddress(requestMap.get("address"));
		user.setLogin("0");
		user.setManager(requestMap.get("manager"));
		user.setReporting(requestMap.get("reporting"));
		user.setMode(requestMap.get("mode"));
		user.setProfilepic("");
		String reporting = requestMap.get("reporting");
		String reportername = userDao.getReporterName(Integer.parseInt(reporting));
		user.setReportername(reportername);
		DeductionGroup dgdi = dgd.getDeductionGroupInfoById(Integer.parseInt(requestMap.get("deductiongroup_id")));
		user.setDeductionGroup(dgdi);
		JSONObject jsonObjectv = new JSONObject(dgdi.getValue());
		double sum = 0;
		sum = sum + jsonObjectv.getDouble("PF") * 12 + jsonObjectv.getDouble("carmaintenance") * 12
				+ jsonObjectv.getDouble("leavetravelallowance") * 12 + jsonObjectv.getDouble("telephoneinternet") * 12
				+ jsonObjectv.getDouble("childreneducationallowance") * 12;
		System.out.println("Sum of selected keys: " + sum);

		Double basic = (Double.parseDouble(requestMap.get("salarypa").replace(",", "")) - sum);
		System.out.println("Basic of selected keys: " + basic);
		user.setBasicpa(basic.toString());

		return user;
	}

	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	com.application.hrms.JWT.jwtUtil jwtUtil;

	@Autowired
	JwtFilter jwtFilter;
	@Autowired
	CustomerUserDetailsService customerUserDetailsService;

	@Autowired
	EmailUtil emailUtil;

	public ResponseEntity<String> login(Map<String, String> requestMap) {
		try {
			System.out.println(HrmsConstants.INVALID_DATA + requestMap);
			Authentication auth = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password")));
			if (auth.isAuthenticated()) {
				String status = customerUserDetailsService.getUserDatails().getStatus();
				if (status.equalsIgnoreCase("y") || status.equalsIgnoreCase("true")) {
					etssi.emplogin(customerUserDetailsService.getUserDatails().getId());
					return new ResponseEntity<String>(
							"{\"token\":\""
									+ jwtUtil.generateToken(customerUserDetailsService.getUserDatails().getEmail(),
											customerUserDetailsService.getUserDatails().getRole(),
											customerUserDetailsService.getUserDatails().getName(),
											customerUserDetailsService.getUserDatails().getId())
									+ "\"}",
							HttpStatus.OK);
				} else {
					return new ResponseEntity<String>("{\"message\":\"" + "Wait for Admin Approvel." + "\"}",
							HttpStatus.BAD_REQUEST);
				}
			}
		} catch (Exception ex) {
			return new ResponseEntity<String>("{\"message\":\"" + "User not Valid." + "\"}", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("{\"message\":\"" + "Bad Credentials." + "\"}", HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> update(Map<String, String> requestMap) {
		try {
			if (jwtFilter.isAdmin()) {
				Optional<User> optional = userDao.findById(Integer.parseInt(requestMap.get("id")));
				if (optional.isPresent()) {

					userDao.updateStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));
//                    sendMailToAllAdmin(requestMap.get("status"), optional.get().getEmail(), userDao.getAllAdmin());
					return HrmsUtils.getResponeEntity("User Status is updated Successfully", HttpStatus.OK);

				} else {
					return HrmsUtils.getResponeEntity("User id doesn't exist", HttpStatus.OK);
				}
			} else {
				return HrmsUtils.getResponeEntity(HrmsConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private void sendMailToAllAdmin(String status, String user, List<String> allAdmin) {
		allAdmin.remove(jwtFilter.getCurrentUsername());
		if (status != null && status.equalsIgnoreCase("true")) {
			emailUtil.SendSimpleMessage(jwtFilter.getCurrentUsername(), "Account Approved",
					"USER:- " + user + "\n is approved by\nADMIN:-" + jwtFilter.getCurrentUsername(), allAdmin);
		} else {
			emailUtil.SendSimpleMessage(jwtFilter.getCurrentUsername(), "Account Disabled",
					"USER:- " + user + "\n is disabled by\nADMIN:-" + jwtFilter.getCurrentUsername(), allAdmin);

		}
	}

	public ResponseEntity<String> checkToken() {
		return HrmsUtils.getResponeEntity("true", HttpStatus.OK);
	}

	public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
		try {
			User user = userDao.findByEmail(jwtFilter.getCurrentUsername());
			if (!user.equals(null)) {
				if (user.getPassword().equals(requestMap.get("oldPassword"))) {
					user.setPassword(requestMap.get("newPassword"));
					userDao.save(user);
					return HrmsUtils.getResponeEntity("Password Updated Successfully", HttpStatus.OK);
				}
				return HrmsUtils.getResponeEntity("Incorrect Old Password", HttpStatus.BAD_REQUEST);
			}
			return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<String> forgetPassword(Map<String, String> requestMap) {
		System.out.println("inside the forgot password function" + requestMap.get("email"));
		try {
			User user = userDao.findByEmail(requestMap.get("email"));
			System.out.println("user email is : " + user.getEmail());
			if (!Objects.isNull(user) && !Strings.isNullOrEmpty(user.getEmail())) {
				// System.out.println("11");
				emailUtil.forgetMail(user.getEmail(), "Credentials by HRMS", user.getPassword());
				return HrmsUtils.getResponeEntity("Check Your mail for Credentials", HttpStatus.OK);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<List<User>> getAllUser() {
		List<User> list = new ArrayList<User>();
		try {
			if (jwtFilter.isAdmin()) {
				return new ResponseEntity<List<User>>(userDao.findAll(), HttpStatus.OK);
			} else {
				return new ResponseEntity<List<User>>(list, HttpStatus.UNAUTHORIZED);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<User>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> updateInfo(Integer id, Map<String, String> requestMap) {
		try {
			if (jwtFilter.isAdmin()) {
				Optional<User> optional = userDao.findById(id);
				if (optional.isPresent()) {
					User user = new User();
					user.setName(requestMap.get("name"));
					user.setContactNumber(requestMap.get("contact"));
					user.setEmail(requestMap.get("email"));
					user.setPassword(optional.get().getPassword());
					user.setStatus(requestMap.get("status"));
					user.setRole(requestMap.get("role"));
					Department dInfo = departmentDao
							.getDepartmentInfoById(Integer.parseInt(requestMap.get("department_id")));
					user.setDepartment(dInfo);
					Designation desig = designationDao
							.getDesignationInfoById(Integer.parseInt(requestMap.get("department_id")));
					user.setDesignation(desig);
					user.setDob(requestMap.get("dob"));
					user.setDoj(requestMap.get("doj"));
					user.setGender(requestMap.get("gender"));
					user.setDor(requestMap.get("dor"));
					user.setSalarypa(requestMap.get("salarypa"));
					user.setMaritalstatus(requestMap.get("maritalstatus"));
					user.setAddress(requestMap.get("address"));
					user.setManager(requestMap.get("manager"));
					user.setReporting(requestMap.get("reporting"));
					String reporting = requestMap.get("reporting");
					String reportername = userDao.getReporterName(Integer.parseInt(reporting));
					user.setReportername(reportername);
					user.setLogin(requestMap.get("login"));
					user.setMode(requestMap.get("mode"));
					user.setId(id);
					user.setProfilepic(optional.get().getProfilepic());
					DeductionGroup dgdi = dgd
							.getDeductionGroupInfoById(Integer.parseInt(requestMap.get("deductiongroup_id")));
					user.setDeductionGroup(dgdi);

					JSONObject jsonObjectv = new JSONObject(dgdi.getValue());
					double sum = 0;
					sum = sum + jsonObjectv.getDouble("PF") * 12 + jsonObjectv.getDouble("carmaintenance") * 12
							+ jsonObjectv.getDouble("leavetravelallowance") * 12
							+ jsonObjectv.getDouble("telephoneinternet") * 12
							+ jsonObjectv.getDouble("childreneducationallowance") * 12;
					System.out.println("Sum of selected keys: " + sum);
					Double basic = (Double.parseDouble(requestMap.get("salarypa").replace(",", "")) - sum);
					System.out.println("Basic of selected keys: " + basic);
					user.setBasicpa(basic.toString());
					userDao.save(user);
					return HrmsUtils.getResponeEntity("User Info is updated Successfully", HttpStatus.OK);

				} else {
					return HrmsUtils.getResponeEntity("User id doesn't exist", HttpStatus.OK);
				}
			} else {
				return HrmsUtils.getResponeEntity(HrmsConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<User>> getUserInfo(Integer id) {
		List<User> list = new ArrayList<User>();
		try {
//			if (jwtFilter.isAdmin()) {
			Optional<User> optional = userDao.findById(id);
			if (optional.isPresent()) {
				return new ResponseEntity<List<User>>(userDao.getUserById(id), HttpStatus.OK);
			} else {
				return new ResponseEntity<List<User>>(list, HttpStatus.UNAUTHORIZED);
			}
//			} else {
//				return new ResponseEntity<List<User>>(list, HttpStatus.UNAUTHORIZED);
//			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<User>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<User>> getManagers() {
		List<User> list = new ArrayList<User>();
		try {
			if (jwtFilter.isAdmin()) {
				return new ResponseEntity<List<User>>(userDao.findAllAdmins(), HttpStatus.OK);
			} else {
				return new ResponseEntity<List<User>>(list, HttpStatus.UNAUTHORIZED);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<User>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> uploadProfilePicture(Integer userId, MultipartFile file) {
		try {
			Optional<User> optionalUser = userDao.findById(userId);
			final String UPLOAD_DIR = "uploads/profile_pictures/" + userId + "/";
			if (optionalUser.isPresent()) {
				User user = optionalUser.get();
				String fileName = userId + "_" + file.getOriginalFilename();
				Path filePath = Paths.get(UPLOAD_DIR + fileName);
				Files.createDirectories(filePath.getParent());
				Files.write(filePath, file.getBytes());
				String filepath = filePath.toString();
//	                user.setProfilepic(filePath.toString());
				userDao.saveProfilepic(userId, filepath);

				return HrmsUtils.getResponeEntity("Profile picture uploaded successfully", HttpStatus.OK);
			} else {
				return HrmsUtils.getResponeEntity("User not found", HttpStatus.NOT_FOUND);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<String> uploadUsers(List<Map<String, Object>> users) {
		List<User> userList = new ArrayList<>();

		for (Map<String, Object> userMap : users) {
			Map<String, String> userStringMap = new HashMap<>();

			for (Map.Entry<String, Object> entry : userMap.entrySet()) {
				userStringMap.put(entry.getKey(), entry.getValue().toString());
			}

			if (validaSignUpMap(userStringMap)) {
				try {
					User usercheck = userDao.findByEmailId(userStringMap.get("email"));
					if (Objects.isNull(usercheck)) {
						User user = getUserFromMap(userStringMap);
						userList.add(user);
					}
				} catch (JSONException e) {
					e.printStackTrace();
					return HrmsUtils.getResponeEntity(HrmsConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
				}
			} else {
				return HrmsUtils.getResponeEntity("Invalid user data", HttpStatus.BAD_REQUEST);
			}
		}

		if (!userList.isEmpty()) {
			// Process userList (e.g., save to DB)
			userDao.saveAll(userList);
			for (User user : userList) {
				User savedUser = userDao.findByEmailId(user.getEmail());
				ls.createleaveBalance(savedUser.getId());
			}
			return HrmsUtils.getResponeEntity("Users Data uploaded successfully", HttpStatus.OK);
		} else {
			return HrmsUtils.getResponeEntity("No valid users found", HttpStatus.BAD_REQUEST);
		}
	}

}