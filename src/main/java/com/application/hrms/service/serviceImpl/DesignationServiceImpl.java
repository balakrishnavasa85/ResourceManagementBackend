package com.application.hrms.service.serviceImpl;
import com.application.hrms.JWT.JwtFilter;
import com.application.hrms.JWT.jwtUtil;
import com.application.hrms.POJO.Department;
import com.application.hrms.POJO.Designation;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.dao.DepartmentDao;
import com.application.hrms.dao.DesignationDao;

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

import com.application.hrms.service.DesignationService;
import com.application.hrms.utils.HrmsUtils;
import com.application.hrms.wrapper.DepartmentWrapper;
import com.application.hrms.wrapper.DesignationWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DesignationServiceImpl implements DesignationService{

	@Autowired
    AuthenticationManager authenticationManager;
	
    @Autowired
    com.application.hrms.JWT.jwtUtil jwtUtil;

    @Autowired
    JwtFilter jwtFilter;
    
    @Autowired
    DesignationDao designationDao;
    
	@Override
	public ResponseEntity<List<DesignationWrapper>> getAll() {

    	List<DesignationWrapper> list = new ArrayList<DesignationWrapper>();
        try {
            if (jwtFilter.isAdmin()) {
                return new ResponseEntity<List<DesignationWrapper>>(designationDao.getAll(), HttpStatus.OK);
            } else {
                return new ResponseEntity<List<DesignationWrapper>>(list, HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<DesignationWrapper>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> update(Map<String, String> requestMap) {
		   try {
	            if (jwtFilter.isAdmin()) {
	                Optional<Designation> optional = designationDao.findById(Integer.parseInt(requestMap.get("id")));
	                	if (optional.isPresent()) {

	                		designationDao.updateStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));
	                    return HrmsUtils.getResponeEntity("Designation Status is updated Successfully", HttpStatus.OK);

	                } else {
	                    return HrmsUtils.getResponeEntity("Designation id doesn't exist", HttpStatus.OK);
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
	public ResponseEntity<List<DesignationWrapper>> getInfo(Integer id) {
		List<DesignationWrapper> list = new ArrayList<DesignationWrapper>();
        try {
            if (jwtFilter.isAdmin()) {
                Optional<Designation> optional = designationDao.findById(id);
                if (optional.isPresent()) {
                	return new ResponseEntity<List<DesignationWrapper>>(designationDao.getDesignationById(id), HttpStatus.OK);                	
                } 
                else {
                	return new ResponseEntity<List<DesignationWrapper>>(list, HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<List<DesignationWrapper>>(list, HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<DesignationWrapper>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
        
	}

	@Override
	public ResponseEntity<String> updateInfo(Integer id, Map<String, String> requestMap) {
		 try {
	            if (jwtFilter.isAdmin()) {
	                Optional<Designation> optional = designationDao.findById(id);
	                	if (optional.isPresent()) {
	                		Designation department = new Designation();
	                		department.setName(requestMap.get("name"));
	                		department.setStatus(requestMap.get("status"));
	                		department.setId(id);
	                		designationDao.save(department);
	                    return HrmsUtils.getResponeEntity("Designation Info is updated Successfully", HttpStatus.OK);

	                } else {
	                    return HrmsUtils.getResponeEntity("Designation id doesn't exist", HttpStatus.OK);
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
	public ResponseEntity<String> create(Map<String, String> requestMap) {
		 try {
	            	Designation department = designationDao.findByName(requestMap.get("name"));
	                if (Objects.isNull(department)) {
	                	designationDao.save(getDepartmentFromMap(requestMap));
	                    return HrmsUtils.getResponeEntity("Successfully  Created.", HttpStatus.OK);
	                } else {
	                    return HrmsUtils.getResponeEntity("Designation Name already exits.", HttpStatus.BAD_REQUEST);
	                }
	            
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

private Designation getDepartmentFromMap(Map<String, String> requestMap) {
	Designation department = new Designation();
	department.setName(requestMap.get("name"));
	department.setStatus("y");
    return department;
}

}
