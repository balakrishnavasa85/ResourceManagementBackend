package com.application.hrms.service.serviceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.application.hrms.POJO.FormSixteen;
import com.application.hrms.POJO.User;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.dao.FromSixteenDao;
import com.application.hrms.dao.TaxSubmissionDao;
import com.application.hrms.dao.UserDao;
import com.application.hrms.service.FormSixteenService;
import com.application.hrms.utils.HrmsUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FromSixteenServiceImpl implements FormSixteenService {

	@Autowired
	UserDao userdao;

	@Autowired
	FromSixteenDao fromSixteenDao;

	@Autowired
	TaxSubmissionDao tsd;

	@Override
	public ResponseEntity<String> uploadForms(String data, List<MultipartFile> file) throws JSONException, IOException {
		List<Integer> timeList = new ArrayList<>();
		JSONArray jsonArray = new JSONArray(data);
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			Optional<User> useri = userdao.findById((Integer) jsonObject.get("user"));
			List<FormSixteen> optfs = fromSixteenDao.findbyuserid((Integer) jsonObject.get("user"));
			if (useri.isPresent() && optfs.size() == 0) {
				String userId = jsonObject.get("user").toString();
				String assessmentYear = jsonObject.get("assessmentyear").toString();
				String filename = jsonObject.get("filename").toString();
				for (MultipartFile filenew : file) {
					String originalFileName = filenew.getOriginalFilename();
					String fileNameWithoutExtension = originalFileName.substring(0, originalFileName.lastIndexOf('.'));

					if (fileNameWithoutExtension.equals(filename)) {
						timeList.add(Integer.parseInt(userId));
						final String UPLOAD_DIR = "uploads/form16/" + userId + "/";
						String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.'));
						String fileName = "From-16(" + assessmentYear + ")" + fileExtension;
						Path filePath = Paths.get(UPLOAD_DIR + fileName);
						Files.createDirectories(filePath.getParent());
						Files.write(filePath, filenew.getBytes());
						String filepath = filePath.toString();

						// Save the form sixteen data
						FormSixteen formSixteen = new FormSixteen();
						formSixteen.setUser(useri.get());
						formSixteen.setFilepath(filepath);
						formSixteen.setAssesmentyear(assessmentYear);
						fromSixteenDao.save(formSixteen);

						break; // Exit the loop once the file is found and moved
					}
				}
				if (!timeList.isEmpty()) {
					for (Integer tl : timeList) {
						Integer userid = tl;
						tsd.deleteByUser(userid);
					}
				}
				return HrmsUtils.getResponeEntity("Users Data uploaded successfully", HttpStatus.OK);
			} else {
//				return HrmsUtils.getResponeEntity(HrmsConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
				continue;
			}
		}
		if (timeList.isEmpty()) {
			return HrmsUtils.getResponeEntity("No Users Data Available for Upload", HttpStatus.OK);
		} else {
			return HrmsUtils.getResponeEntity(HrmsConstants.INVALID_DATA, HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<List<FormSixteen>> form16byid(Integer userid) {
		List<FormSixteen> list = new ArrayList<FormSixteen>();
		try {
			Optional<User> optional = userdao.findById(userid);
			if (optional.isPresent()) {
				return new ResponseEntity<List<FormSixteen>>(fromSixteenDao.findbyuserid(userid), HttpStatus.OK);
			} else {
				return new ResponseEntity<List<FormSixteen>>(list, HttpStatus.UNAUTHORIZED);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<FormSixteen>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}