package com.application.hrms.service.serviceImpl;
import com.application.hrms.JWT.JwtFilter;
import com.application.hrms.JWT.jwtUtil;
import com.application.hrms.POJO.Department;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.dao.DepartmentDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import com.application.hrms.service.DepartmentService;
import com.application.hrms.utils.HrmsUtils;
import com.application.hrms.wrapper.DepartmentWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
    AuthenticationManager authenticationManager;
	
    @Autowired
    com.application.hrms.JWT.jwtUtil jwtUtil;

    @Autowired
    JwtFilter jwtFilter;
    
    @Autowired
    DepartmentDao departmentDao;
    
	@Override
	public ResponseEntity<List<DepartmentWrapper>> getAllDepartment() {

    	List<DepartmentWrapper> list = new ArrayList<DepartmentWrapper>();
        try {
            if (jwtFilter.isAdmin()) {
                return new ResponseEntity<List<DepartmentWrapper>>(departmentDao.getAllDepartments(), HttpStatus.OK);
            } else {
                return new ResponseEntity<List<DepartmentWrapper>>(list, HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<DepartmentWrapper>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> update(Map<String, String> requestMap) {
		   try {
	            if (jwtFilter.isAdmin()) {
	                Optional<Department> optional = departmentDao.findById(Integer.parseInt(requestMap.get("id")));
	                	if (optional.isPresent()) {

	                		departmentDao.updateStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));
	                    return HrmsUtils.getResponeEntity("Department Status is updated Successfully", HttpStatus.OK);

	                } else {
	                    return HrmsUtils.getResponeEntity("Department id doesn't exist", HttpStatus.OK);
	                }
	            } else {
	                return HrmsUtils.getResponeEntity(HrmsConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<DepartmentWrapper>> getDepartmentInfo(Integer id) {
		List<DepartmentWrapper> list = new ArrayList<DepartmentWrapper>();
        try {
            if (jwtFilter.isAdmin()) {
                Optional<Department> optional = departmentDao.findById(id);
                if (optional.isPresent()) {
                	return new ResponseEntity<List<DepartmentWrapper>>(departmentDao.getDepartmentById(id), HttpStatus.OK);                	
                } 
                else {
                	return new ResponseEntity<List<DepartmentWrapper>>(list, HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<List<DepartmentWrapper>>(list, HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<DepartmentWrapper>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
        
	}

	@Override
	public ResponseEntity<String> updateInfo(Integer id, Map<String, String> requestMap) {
		 try {
	            if (jwtFilter.isAdmin()) {
	                Optional<Department> optional = departmentDao.findById(id);
	                	if (optional.isPresent()) {
	                		Department department = new Department();
	                		department.setName(requestMap.get("name"));
	                		department.setStatus(requestMap.get("status"));
	                		department.setId(id);
	                		departmentDao.save(department);
	                    return HrmsUtils.getResponeEntity("Department Info is updated Successfully", HttpStatus.OK);

	                } else {
	                    return HrmsUtils.getResponeEntity("Department id doesn't exist", HttpStatus.OK);
	                }
	            } else {
	                return HrmsUtils.getResponeEntity(HrmsConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG+"1", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> create(Map<String, String> requestMap) {
		 try {
	            	Department department = departmentDao.findByName(requestMap.get("name"));
	                if (Objects.isNull(department)) {
	                	departmentDao.save(getDepartmentFromMap(requestMap));
	                    return HrmsUtils.getResponeEntity("Successfully  Created.", HttpStatus.OK);
	                } else {
	                    return HrmsUtils.getResponeEntity("Departmnt Name already exits.", HttpStatus.BAD_REQUEST);
	                }
	            
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

private Department getDepartmentFromMap(Map<String, String> requestMap) {
	Department department = new Department();
	department.setName(requestMap.get("name"));
	department.setStatus("y");
    return department;
}

}
