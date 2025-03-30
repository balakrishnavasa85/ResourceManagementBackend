package com.application.hrms.rest;

import org.json.JSONArray;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.hrms.POJO.Leaves;
import com.application.hrms.POJO.Schdules;
import com.application.hrms.wrapper.DepartmentWrapper;
import com.application.hrms.wrapper.RecruitmentDetailsDTO;
import com.application.hrms.wrapper.RelationWrapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping(path = "/schdules")
public interface SchdulesRest {

    @PostMapping(path = "/create")
    public ResponseEntity<String> create(@RequestBody(required = true) Map<String, String> requestMap);
    
    @GetMapping(path= "/checkUserInterviews/{id}")
    public ResponseEntity<Boolean> checkUserInterviews(@PathVariable Integer id);

    @GetMapping(path= "/checkInterviewDetailsById/{id}", produces = "application/json")
    public ResponseEntity<List<RecruitmentDetailsDTO>> checkInterviewDetailsById(@PathVariable Integer id);
    
    @PostMapping(path="/updateFeedback")
    public ResponseEntity<String> updateFeedback(@RequestBody(required = true) Map<String, String> requestMap);
    
    @GetMapping(path= "/checkPreviousHistory/{id}")
    public ResponseEntity<List<Schdules>> checkPreviousHistory(@PathVariable Integer id);
    
}
