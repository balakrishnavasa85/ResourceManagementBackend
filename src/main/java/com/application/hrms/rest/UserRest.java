package com.application.hrms.rest;

import com.application.hrms.POJO.User;
import com.application.hrms.wrapper.UserWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/user")
public interface UserRest {
    @PostMapping(path = "/signup")
    public ResponseEntity<String> signUp(@RequestBody(required = true) Map<String, String> requestMap);
    
    @PostMapping(path = "/uploadUsers")
    public ResponseEntity<String> uploadUsers(@RequestBody List<Map<String, Object>> users);

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody(required = true) Map<String, String> requestMap);

    @GetMapping(path = "/get")
//    public ResponseEntity<List<User>> getAllUser(
//            @RequestHeader(value = "Authorization", required = false) String authorizationHeader);
  public ResponseEntity<List<User>> getAllUser(); 
    @GetMapping(path = "/getInfo/{id}")
    public ResponseEntity<List<User>> getUserInfo(@PathVariable Integer id);
    
    @PostMapping(path = "/update")
    public ResponseEntity<String> update(@RequestBody(required = true) Map<String, String> requestMap);
    
    @PostMapping(path = "/updateInfo/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Integer id, @RequestBody(required = true) Map<String, String> requestMap);

    @GetMapping(path = "/checkToken")
    public ResponseEntity<String> checkToken();

    @PostMapping(path = "/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody Map<String, String> requestMap);

    @PostMapping(path = "/forgotPassword")
    public ResponseEntity<String> forgetPassword(@RequestBody Map<String, String> requestMap);

    @GetMapping(path = "/getManagers")
    public ResponseEntity<List<User>> getManagers();
    
    @PostMapping("/uploadProfilePicture/{userId}")
    public ResponseEntity<String> uploadProfilePicture(@PathVariable Integer userId, @RequestParam("file") MultipartFile file);
}

