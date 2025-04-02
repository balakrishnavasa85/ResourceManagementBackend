package com.application.hrms.service.serviceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.application.hrms.JWT.JwtFilter;
import com.application.hrms.JWT.jwtUtil;
import com.application.hrms.POJO.Department;
import com.application.hrms.POJO.Designation;
import com.application.hrms.POJO.Recrutment;
import com.application.hrms.POJO.RecrutmentAssigners;
import com.application.hrms.POJO.Tds;
import com.application.hrms.POJO.User;
import com.application.hrms.POJO.UserProcess;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.dao.DepartmentDao;
import com.application.hrms.dao.DesignationDao;
import com.application.hrms.dao.RecrutmentAssignersDao;
import com.application.hrms.dao.RecrutmentDao;
import com.application.hrms.dao.TdsDao;
import com.application.hrms.dao.UserDao;
import com.application.hrms.dao.UserProcessDao;
import com.application.hrms.service.RecrutmentService;
import com.application.hrms.service.TdsService;
import com.application.hrms.utils.HrmsUtils;
import com.application.hrms.wrapper.DepartmentWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecrutmentServiceImpl implements RecrutmentService {

	@Autowired
	RecrutmentDao recrutmentDao;

	@Autowired
	DepartmentDao departmentDao;
	
	@Autowired
	DesignationDao designationDao;
	
	@Autowired
	JwtFilter jwtFilter;
	
	@Autowired
	UserDao userdao;
	
	@Autowired
	UserProcessDao userprocessDao;
	
	@Autowired
	RecrutmentAssignersDao recrutmentAssignerDao;

	@Override
	public ResponseEntity<String> createrecrtment(Map<String, String> requestMap) {
		try {
			String title = requestMap.get("title");
			Optional<Recrutment> rinfo = recrutmentDao.findByTitle(title);
			if (jwtFilter.isAdmin()) {
			if (!rinfo.isPresent()) {
				Recrutment rdata = new Recrutment();
				rdata.setTitle(title);
				rdata.setBudget(requestMap.get("budget"));
				rdata.setDescription(requestMap.get("description"));
				rdata.setNoofpositions(Integer.parseInt(requestMap.get("noofpositions")));
				rdata.setStatus("y");
				Department dInfo = departmentDao
						.getDepartmentInfoById(Integer.parseInt(requestMap.get("department_id")));
				Designation degInfo = designationDao.getDesignationInfoById(Integer.parseInt(requestMap.get("position_id")));
				rdata.setDepartment(dInfo);
				rdata.setDesignation(degInfo);
				recrutmentDao.save(rdata);
				return HrmsUtils.getResponeEntity("Recrutment Datails Created.", HttpStatus.OK);
			} else {
				return HrmsUtils.getResponeEntity("Email already exits.", HttpStatus.BAD_REQUEST);
			}} else {
				return HrmsUtils.getResponeEntity(HrmsConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Recrutment>> getActive() {
		List<Recrutment> list = new ArrayList<Recrutment>();
		try {
			List<Recrutment> rlist = recrutmentDao.getAllActive();
			
			return new ResponseEntity<List<Recrutment>>(rlist, HttpStatus.OK);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<Recrutment>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Recrutment>> getRecruitmentsByUserId(Integer userid) {
		List<Recrutment> list = new ArrayList<Recrutment>();
		try {
			List<Recrutment> rlist = recrutmentDao.findRecruitmentsByUserId(userid);
			
			return new ResponseEntity<List<Recrutment>>(rlist, HttpStatus.OK);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<Recrutment>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> creatUserProcess(String data, MultipartFile file) throws JSONException, IOException{
		JSONObject jsonObject = new JSONObject(data);
		Optional<RecrutmentAssigners> assigner = recrutmentAssignerDao.findById((Integer) jsonObject.get("assignerid"));
//		Optional<User> useri = userdao.findById((Integer) jsonObject.get("userid"));
//		Optional<Recrutment> rinfo = recrutmentDao.findById((Integer) jsonObject.get("reqid"));
		if(assigner.isPresent())
		{
			String originalFileName = file.getOriginalFilename();
			String fileNameWithoutExtension = originalFileName.substring(0, originalFileName.lastIndexOf('.'));

//			if (fileNameWithoutExtension.equals(file)) {
				final String UPLOAD_DIR = "uploads/recrutment-process/"+jsonObject.get("recrutmentid") +"/";
				String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.'));
				String fileName =  jsonObject.get("name") + fileExtension;
				Path filePath = Paths.get(UPLOAD_DIR + fileName);
				Files.createDirectories(filePath.getParent());
				Files.write(filePath, file.getBytes());
				String filepath = filePath.toString();
				
				UserProcess userprocessinfo = new UserProcess();
				userprocessinfo.setAadhar(jsonObject.get("aadhar").toString());
				userprocessinfo.setContact(jsonObject.get("contact").toString());
				userprocessinfo.setDob(jsonObject.get("dob").toString());
				userprocessinfo.setEmail(jsonObject.get("email").toString());
				userprocessinfo.setExpectedsalary(jsonObject.get("expectedsalary").toString());
				userprocessinfo.setExperianceinmonths(jsonObject.get("experianceinmonths").toString());
				userprocessinfo.setFilepath(filepath);
				userprocessinfo.setJoiningon(jsonObject.get("joiningon").toString());
				userprocessinfo.setName(jsonObject.get("name").toString());
				userprocessinfo.setPan(jsonObject.get("pan").toString());
				userprocessinfo.setRecrutmentassigners(assigner.get());
				userprocessinfo.setAddress(jsonObject.get("address").toString());
				userprocessDao.save(userprocessinfo);
				return HrmsUtils.getResponeEntity("User Entry Created.", HttpStatus.OK);
//			} else {
//				return HrmsUtils.getResponeEntity("File Not Available.", HttpStatus.BAD_REQUEST);
//			}
			} else {
				return HrmsUtils.getResponeEntity("User or Recrutment Id not Exists.", HttpStatus.BAD_REQUEST);
			}
	
		// TODO Auto-generated method stub
//		return HrmsUtils.getResponeEntity("User or Recrutment Id not Exists.", HttpStatus.BAD_REQUEST);
	}
}
