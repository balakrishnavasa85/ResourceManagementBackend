package com.application.hrms.service.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.application.hrms.POJO.Recrutment;
import com.application.hrms.POJO.Schdules;
import com.application.hrms.POJO.User;
import com.application.hrms.POJO.UserProcess;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.dao.RecrutmentDao;
import com.application.hrms.dao.SchdulesDao;
import com.application.hrms.dao.UserDao;
import com.application.hrms.dao.UserProcessDao;
import com.application.hrms.service.SchdulesService;
import com.application.hrms.utils.EmailUtil;
import com.application.hrms.utils.HrmsUtils;
import com.application.hrms.wrapper.RecruitmentDetailsDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SchdulesServiceImpl implements SchdulesService {

	@Autowired
	SchdulesDao sDao;

	@Autowired
	UserProcessDao upd;

	@Autowired
	UserDao userDao;

	@Autowired
	RecrutmentDao recrutmentDao;
	
	@Autowired
	EmailUtil emailUtil;

	@Override
	public ResponseEntity<String> create(Map<String, String> requestMap) {
		try {
			Optional<UserProcess> useri = upd.findById(Integer.parseInt(requestMap.get("userid")));
			if (useri.isPresent()) {
				String name = userDao.getUserName(Integer.parseInt(requestMap.get("interviewerid")));

				Schdules sch = new Schdules();
				sch.setInterviewerid(Integer.parseInt(requestMap.get("interviewerid")));
				sch.setInterviewername(name);
				sch.setInterviewtime(requestMap.get("interviewtime"));
				sch.setInterviewlink(requestMap.get("interviewlink"));
				sch.setInterviewTitle(requestMap.get("title"));
				sch.setStatus(requestMap.get("status"));
				sch.setUserprocess(useri.get());
				sch.setAssigner(Integer.parseInt(requestMap.get("assignerid")));
				sch.setTakentime(null);
				sch.setComment(null);
				sDao.save(sch);

				Map<String, String> info = new HashMap<>();
				info.put("interviewlink", requestMap.get("interviewlink"));
				info.put("interviewername", useri.get().getName());
				info.put("interviewtime", requestMap.get("interviewtime"));
				emailUtil.sendHtmlEmail(useri.get().getEmail(),
						requestMap.get("title"), info, null, "schdule");

				return HrmsUtils.getResponeEntity("Successfully Schduled.", HttpStatus.OK);
			} else {
				return HrmsUtils.getResponeEntity("Request Not Submited", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<Boolean> checkUserInterviews(Integer id) {
		Schdules list = new Schdules();
		try {
			List<Schdules> data = sDao.checkUserInterviews(id);
			if (data.size() > 0) {
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			} else {
				return new ResponseEntity<Boolean>(false, HttpStatus.OK);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<RecruitmentDetailsDTO>> checkInterviewDetailsById(Integer id) {
		List<RecruitmentDetailsDTO> list = new ArrayList<RecruitmentDetailsDTO>();
		try {
			List<RecruitmentDetailsDTO> data = sDao.findSchedulesByInterviewerId(id);
			return new ResponseEntity<List<RecruitmentDetailsDTO>>(data, HttpStatus.OK);
//
//			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<RecruitmentDetailsDTO>>(list,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> updateFeedback(Map<String, String> requestMap) {
		try {
			Optional<Schdules> sci = sDao.findById(Integer.parseInt(requestMap.get("schduleid")));
			if (sci.isPresent()) {
				String comment = requestMap.get("comment");
				String takentime = requestMap.get("takentime");
				String status = requestMap.get("status");
				Integer schduleid = Integer.parseInt(requestMap.get("schduleid"));
				sDao.updateFeedback(comment, schduleid, takentime , status);
				return HrmsUtils.getResponeEntity("Successfully Updated.", HttpStatus.OK);
			} else {
				return HrmsUtils.getResponeEntity("Request Not Submited", HttpStatus.BAD_REQUEST);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Schdules>> checkPreviousHistory(Integer id) {
		try {
			return new ResponseEntity<List<Schdules>>(sDao.checkPreviousHistory(id), HttpStatus.OK);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return new ResponseEntity<List<Schdules>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<RecruitmentDetailsDTO>> selectedUser() {
		List<RecruitmentDetailsDTO> list = new ArrayList<RecruitmentDetailsDTO>();
		try {
			List<RecruitmentDetailsDTO> data = sDao.slectedUser();
			return new ResponseEntity<List<RecruitmentDetailsDTO>>(data, HttpStatus.OK); 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<RecruitmentDetailsDTO>>(list,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
