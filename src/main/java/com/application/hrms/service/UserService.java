package com.application.hrms.service;

import com.application.hrms.POJO.User;
import com.application.hrms.wrapper.UserWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface UserService {
   ResponseEntity<String> signUp(Map<String,String> requestMap);

    ResponseEntity<String> login(Map<String, String> requestMap);

    ResponseEntity<List<User>> getAllUser();
    ResponseEntity<List<User>> getManagers();

    ResponseEntity<String> update(Map<String, String> requestMap);

    ResponseEntity<String> checkToken();

    ResponseEntity<String> changePassword(Map<String, String> requestMap);

    ResponseEntity<String> forgetPassword(Map<String, String> requestMap);

	ResponseEntity<String> updateInfo(Integer id, Map<String, String> requestMap);

	ResponseEntity<List<User>> getUserInfo(Integer id); 
	
	ResponseEntity <String> uploadProfilePicture(Integer id,MultipartFile file);
	
	ResponseEntity <String> uploadUsers(List<Map<String, Object>> users); 
	
	ResponseEntity<List<User>> getRepoterDetails(Integer userId);
	
	ResponseEntity<List<User>> getRepotingDetails(Integer userId);
	
}
