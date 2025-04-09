package com.application.hrms.service.serviceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.application.hrms.POJO.Recrutment;
import com.application.hrms.POJO.User;
import com.application.hrms.POJO.UserProcess;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.dao.RecrutmentDao;
import com.application.hrms.dao.UserDao;
import com.application.hrms.dao.UserProcessDao;
import com.application.hrms.service.UserProcessService;
import com.application.hrms.service.UserService;
import com.application.hrms.utils.EmailUtil;
import com.application.hrms.utils.HrmsUtils;
import com.application.hrms.wrapper.UserProcessDetailsDTO;
import com.application.hrms.wrapper.UserProcessWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserProcessServiceImpl implements UserProcessService {

	@Autowired
	UserProcessDao userProcessDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RecrutmentDao recrutmentDao;
	
	@Autowired
	EmailUtil emailUtil;

	@Override
	public ResponseEntity<List<UserProcess>> check(Map<String, String> requestMap) {
		List<UserProcess> up = new ArrayList<UserProcess>();
		try {
			String aadhar = requestMap.get("aadhar");
			String pan = requestMap.get("pan");
			return new ResponseEntity<List<UserProcess>>(userProcessDao.checkValue(aadhar, pan), HttpStatus.OK);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return new ResponseEntity<List<UserProcess>>(up, HttpStatus.INTERNAL_SERVER_ERROR);
	}
 
	@Override
	public ResponseEntity<String> updateUserConformation(Map<String, String> requestMap) {
		try {
			Optional<UserProcess> up = userProcessDao.findById(Integer.parseInt(requestMap.get("userProcessId")));
			if(up.isPresent()) {
				LocalDate currentDate = LocalDate.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				
				Integer opuserid = Integer.parseInt(requestMap.get("opesuserid"));
				String opusername = userDao.getUserName(Integer.parseInt(requestMap.get("opesuserid")));
				String opusercomment = requestMap.get("comment");
				String preferedjoingdate = requestMap.get("preferedJoiningDate");
				String userconformation = requestMap.get("userConformation");
				String operationdate = currentDate.format(formatter);
				Integer id = Integer.parseInt(requestMap.get("userProcessId"));
				userProcessDao.updateOperationData(opuserid,opusername,opusercomment,preferedjoingdate,userconformation,operationdate,id);
				
				if(requestMap.get("userConformation").equals("accepted")) {					
				Map<String, String> info = new HashMap<>();
				info.put("name", up.get().getName());
				emailUtil.sendHtmlEmail(up.get().getEmail(),
						"User Conformation", info, null, "conformation");
				}
				
				
				return HrmsUtils.getResponeEntity("Successfully  Updated.", HttpStatus.OK);
			}
			else
			{
				return HrmsUtils.getResponeEntity("Opsteam not valid.", HttpStatus.NOT_FOUND);				
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> offerInitiate(Map<String, String> requestMap) {
		try {
			Optional<UserProcess> up = userProcessDao.findById(Integer.parseInt(requestMap.get("userProcessId")));
			if(up.isPresent()) { 
				String offergenerateddate = requestMap.get("offergenerateddate");
				String password = requestMap.get("password");
				String userlink = requestMap.get("userlink");
				Integer id = Integer.parseInt(requestMap.get("userProcessId"));				
				userProcessDao.offerInitiate(offergenerateddate,password,userlink,id);
				
				Map<String, String> info = new HashMap<>();
				info.put("name", up.get().getName());
				info.put("email", up.get().getEmail());
				info.put("password", password);
				info.put("offergeneratedon", offergenerateddate);
				info.put("joingdate", up.get().getpreferedjoingdate());
				emailUtil.sendHtmlEmail(up.get().getEmail(),
						"Congratulations", info, null, "offer");
				
				return HrmsUtils.getResponeEntity("Successfully  Updated.", HttpStatus.OK);
			}
			else
			{
				return HrmsUtils.getResponeEntity("Opsteam not valid.", HttpStatus.NOT_FOUND);				
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<UserProcess> getyUser(Integer id) {
		UserProcess up = new UserProcess();
		try { 
			Optional<UserProcess> val = userProcessDao.findById(id);
			return new ResponseEntity<UserProcess>(val.get(), HttpStatus.OK);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return new ResponseEntity<UserProcess>(up, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> updateUserInformation(Map<String, String> requestMap) {
		try {
			Optional<UserProcess> up = userProcessDao.findById(Integer.parseInt(requestMap.get("id")));
			if(up.isPresent()) {
				String address = requestMap.get("address");
				String contact = requestMap.get("contact");
				String dob = requestMap.get("dob");
				String gender = requestMap.get("gender");
				String maritalstatus = requestMap.get("maritalstatus");
				String name = requestMap.get("name");
				Integer id = Integer.parseInt(requestMap.get("id"));
				
				userProcessDao.updateUserInformation(address,contact,dob,gender,maritalstatus,name,id);
				return HrmsUtils.getResponeEntity("Successfully  Updated.", HttpStatus.OK);
			}
			else
			{
				return HrmsUtils.getResponeEntity("Opsteam not valid.", HttpStatus.NOT_FOUND);				
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<UserProcessDetailsDTO>> getAcceptedUsersList() {
		List<UserProcessDetailsDTO> up = new ArrayList<UserProcessDetailsDTO>();
		try { 
			return new ResponseEntity<List<UserProcessDetailsDTO>>(userProcessDao.getAcceptedUsersList(), HttpStatus.OK);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return new ResponseEntity<List<UserProcessDetailsDTO>>(up, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> onboard(Integer userProcessId, Integer recrutmentId, Map<String, String> requestMap) {
		try {
			Optional<Recrutment> rval = recrutmentDao.findById(recrutmentId);
			if(rval.isPresent() && rval.get().getNoofpositions() >= rval.get().getNoofpositionsclosed() )
			{
				Optional<UserProcess> up = userProcessDao.findById(userProcessId);
				if(up.isPresent()) {					
					ResponseEntity<String> response = userService.signUp(requestMap);
					HttpStatus status = response.getStatusCode();
					if(status.equals(HttpStatus.OK))
					{
						User newuser = userDao.findByEmailId(requestMap.get("email"));
						userProcessDao.updateOnboard(userProcessId,newuser.getUniqueId());
						Integer noofopositionsCount = rval.get().getNoofpositionsclosed() + 1;						
						recrutmentDao.updatePositions(noofopositionsCount,recrutmentId);						
						return HrmsUtils.getResponeEntity("Successfully  Onboareded.", HttpStatus.OK);
					} 
					else
					{
						return HrmsUtils.getResponeEntity("User Onboard not Success check the Details.", HttpStatus.NOT_FOUND);		
					}
				}
				else
				{
					return HrmsUtils.getResponeEntity("Process User not Found.", HttpStatus.NOT_FOUND);				
				}
			}
			else
			{

				return HrmsUtils.getResponeEntity("Positions are Closed", HttpStatus.NOT_FOUND);	
			}
			

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
