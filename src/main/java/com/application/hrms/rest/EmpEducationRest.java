package com.application.hrms.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.application.hrms.POJO.EmpEducation;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/empeducation")
public interface EmpEducationRest {

	@GetMapping(path = "/getInfo/{id}")
	public ResponseEntity<List<EmpEducation>> getEmpEducationInfo(@PathVariable Integer id);

	@PutMapping(path = "/updateInfo/{id}")
	public ResponseEntity<String> updateEmpEducation(@PathVariable Integer id,
			@RequestBody(required = true) Map<String, String> requestMap);

	@PostMapping(path = "/create/{id}")
	public ResponseEntity<String> create(@PathVariable Integer id,
            @RequestParam("educations") List<String> educations, // Array of education data in String format (JSON string)
            @RequestParam("files") List<MultipartFile> files);

	@GetMapping(path = "/getEmpEducationInfo/{id}")
	public ResponseEntity<List<EmpEducation>> getUserEmpEducationInfo(@PathVariable Integer id);
}
