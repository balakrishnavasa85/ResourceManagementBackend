package com.application.hrms.rest.restImpl;

import com.application.hrms.JWT.jwtUtil;
import com.application.hrms.POJO.User;
import com.application.hrms.constents.HrmsConstants;  
import com.application.hrms.rest.UserRest;
import com.application.hrms.service.UserService;
import com.application.hrms.utils.HrmsUtils;
import com.application.hrms.wrapper.UserWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@RestController
public class UserRestImpl implements UserRest {

    @Autowired
    UserService userService;

    public ResponseEntity<String> signUp(Map<String, String> requestMap) {

        try {
            //System.out.println("inside userRestImpl");
            return userService.signUp(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //System.out.println("Before return");
        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<String> login(Map<String, String> requestMap) {
        try {
            return userService.login(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    
    public ResponseEntity<List<User>> getAllUser() { 
        try {
        	   
//        	JSONObject data = jwtUtil.extractUserIdNew(authorizationHeader);
            
            return userService.getAllUser();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<User>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }



    
    public ResponseEntity<String> update(Map<String, String> requestMap) {

        try {
            return userService.update(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    
    public ResponseEntity<String> checkToken() {
        try {
            return userService.checkToken();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
        try {
            return userService.changePassword(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    
    public ResponseEntity<String> forgetPassword(Map<String, String> requestMap) {
        try {
            return userService.forgetPassword(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

	@Override
	public ResponseEntity<String> updateUser(Integer id, Map<String, String> requestMap) {
		 try {
	            return userService.updateInfo(id,requestMap);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<User>> getUserInfo(Integer id) {
		  try {
	            return userService.getUserInfo(id);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return new ResponseEntity<List<User>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);
	  
	}

	@Override
	public ResponseEntity<List<User>> getManagers() {
		 try {
	            //System.out.println("inside userRestImpl");
	            return userService.getManagers();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        //System.out.println("Before return");
	       return new ResponseEntity<List<User>>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@Override
	public ResponseEntity<String> uploadProfilePicture(Integer userId, MultipartFile file) {
		try {
            //System.out.println("inside userRestImpl");
            return userService.uploadProfilePicture(userId,file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //System.out.println("Before return");
       return new ResponseEntity<String>((MultiValueMap<String, String>) new ArrayList<Object>(), HttpStatus.INTERNAL_SERVER_ERROR);

	}

	 
}
