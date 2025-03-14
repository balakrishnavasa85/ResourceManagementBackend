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

import com.application.hrms.POJO.FormSixteen;
import com.application.hrms.POJO.Leaves;
import com.application.hrms.wrapper.DepartmentWrapper;
import com.application.hrms.wrapper.RelationWrapper;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/from16")
public interface FromSixteenRest {

	@PostMapping(path = "/uploadForms")
	public ResponseEntity<String> uploadForms(@RequestParam("exceldata") String data,
			@RequestParam("file") List<MultipartFile> files);
	
	@GetMapping(path = "/form16byid/{userid}")
	public ResponseEntity<List<FormSixteen>> form16byid(@PathVariable Integer userid );
}
