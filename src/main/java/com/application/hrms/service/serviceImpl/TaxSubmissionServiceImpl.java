package com.application.hrms.service.serviceImpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.application.hrms.POJO.TaxSubmission;
import com.application.hrms.POJO.User;
import com.application.hrms.dao.TaxSubmissionDao;
import com.application.hrms.dao.UserDao;
import com.application.hrms.service.TaxSubmissionService;
import com.application.hrms.utils.HrmsUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TaxSubmissionServiceImpl implements TaxSubmissionService {
	@Autowired
	TaxSubmissionDao tdd;

	@Autowired
	UserDao userDao;

	@Override
	public ResponseEntity<String> create(Integer user, Map<String, String> requestMap) throws JSONException {
		Optional<User> optional = userDao.findById(user);
		if (optional.isPresent()) {
			Optional<TaxSubmission> sd = tdd.findByUser(user);
			TaxSubmission tsdData = new TaxSubmission();
			tsdData = TaxSubmissionMap(requestMap, optional.get());
			tdd.save(tsdData);
			return HrmsUtils.getResponeEntity("Tax Details uploaded successfully", HttpStatus.OK);
		} else {
			return HrmsUtils.getResponeEntity("User not found", HttpStatus.NOT_FOUND);
		}

	}

	private TaxSubmission TaxSubmissionMap(Map<String, String> taxSubmissionData, User useri) throws JSONException {
		TaxSubmission sd = new TaxSubmission();
		if (taxSubmissionData.get("id") != null) {
			sd.setId(Integer.parseInt(taxSubmissionData.get("id")));
		}
		sd.setdonationMode(taxSubmissionData.get("donationMode"));
		sd.seteducationLoanProvider(taxSubmissionData.get("educationLoanProvider"));
		sd.setlandlordPAN(taxSubmissionData.get("landlordPAN"));
		sd.setpropertyValue(taxSubmissionData.get("propertyValue"));
		sd.setrentPaid(taxSubmissionData.get("rentPaid"));
		sd.setsection80C(taxSubmissionData.get("section80C"));
		sd.setsection80CCD1B(taxSubmissionData.get("section80CCD1B"));
		sd.setsection80D(taxSubmissionData.get("section80D"));
		sd.setsection80DD(taxSubmissionData.get("section80DD"));
		sd.setsection80E(taxSubmissionData.get("section80E"));
		sd.setsection80EE(taxSubmissionData.get("section80EE"));
		sd.setsection80EEA(taxSubmissionData.get("section80EEA"));
		sd.setsection80EEB(taxSubmissionData.get("section80EEB"));
		sd.setsection80G(taxSubmissionData.get("section80G"));
		sd.setsection80TTA(taxSubmissionData.get("section80TTA"));
		sd.setsection80TTB(taxSubmissionData.get("section80TTB"));
		sd.setStatus("y");
		sd.setassesmentyear(taxSubmissionData.get("assesmentyear"));
		sd.setUser(useri);
		return sd;

	}

	@Override
	public ResponseEntity<TaxSubmission> getDetails(Integer user) {
		Optional<TaxSubmission> sd = tdd.findByUser(user);
		if (sd.isPresent()) {
			return new ResponseEntity<TaxSubmission>(sd.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<TaxSubmission>(HttpStatus.NO_CONTENT);
		}
	}

	@Override
	public ResponseEntity<List<TaxSubmission>> getall() {
		List<TaxSubmission> sd = tdd.findAll();
		if (sd.size() > 0) {
			return new ResponseEntity<List<TaxSubmission>>(sd, HttpStatus.OK);
		} else {
			return new ResponseEntity<List<TaxSubmission>>(HttpStatus.NO_CONTENT);
		}
	}
}
