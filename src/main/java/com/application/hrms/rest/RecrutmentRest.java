package com.application.hrms.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.application.hrms.POJO.Recrutment;

@RequestMapping(path = "/recrutment")
public interface RecrutmentRest {
    @PostMapping(path = "/create")
    public ResponseEntity<String> createrecrtment(@RequestBody(required = true) Map<String, String> requestMap);
    
    @GetMapping(path = "/getActive")
    public ResponseEntity<List<Recrutment>> getActive();
    
    @GetMapping(path = "/getRecruitmentsByUserId/{userid}")
    public ResponseEntity<List<Recrutment>> getRecruitmentsByUserId(@PathVariable Integer userid);
    
    @PostMapping(path = "/creatUserProcess")
    public ResponseEntity<String> creatUserProcess(@RequestParam("userdata") String data,
			@RequestParam("file") MultipartFile file);
}
