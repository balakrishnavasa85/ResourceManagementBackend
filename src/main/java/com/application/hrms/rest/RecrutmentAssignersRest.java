package com.application.hrms.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.hrms.POJO.Recrutment;
import com.application.hrms.POJO.RecrutmentAssigners;

@RequestMapping(path = "/recrutmentassigners")
public interface RecrutmentAssignersRest {
    @PostMapping(path = "/create")
    public ResponseEntity<String> createrecrtmentassign(@RequestBody(required = true) Map<String, String> requestMap);
    
//    @GetMapping(path ="/getById/{id}")
//    public ResponseEntity<Recrutment> getById(@PathVariable Integer id);

    @GetMapping(path = "/getById/{id}")
    public ResponseEntity<List<Recrutment>> getById(@PathVariable Integer id);
}
