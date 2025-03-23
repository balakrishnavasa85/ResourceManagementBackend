package com.application.hrms.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.hrms.POJO.Recrutment;

@RequestMapping(path = "/recrutment")
public interface RecrutmentRest {
    @PostMapping(path = "/create")
    public ResponseEntity<String> createrecrtment(@RequestBody(required = true) Map<String, String> requestMap);
    

    @GetMapping(path = "/getActive")
    public ResponseEntity<List<Recrutment>> getActive();
}
