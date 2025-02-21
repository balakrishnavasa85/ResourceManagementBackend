package com.application.hrms.service.serviceImpl;
import com.application.hrms.JWT.JwtFilter;
import com.application.hrms.JWT.jwtUtil;
import com.application.hrms.POJO.DeductionGroup;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.dao.DeductionGroupDao;

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

import com.application.hrms.service.DeductionGroupService;
import com.application.hrms.utils.HrmsUtils;
import com.application.hrms.wrapper.DeductionGroupWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DeductionGroupServiceImpl implements DeductionGroupService{

	@Autowired
    AuthenticationManager authenticationManager;
	
    @Autowired
    com.application.hrms.JWT.jwtUtil jwtUtil;

    @Autowired
    JwtFilter jwtFilter;
    
    @Autowired
    DeductionGroupDao deductionGroupDao;
    
	@Override
	public ResponseEntity<List<DeductionGroupWrapper>> getAll() {

    	List<DeductionGroupWrapper> list = new ArrayList<DeductionGroupWrapper>();
        try {
            if (jwtFilter.isAdmin()) {
                return new ResponseEntity<List<DeductionGroupWrapper>>(deductionGroupDao.getAll(), HttpStatus.OK);
            } else {
                return new ResponseEntity<List<DeductionGroupWrapper>>(list, HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<DeductionGroupWrapper>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> update(Map<String, String> requestMap) {
		   try {
	            if (jwtFilter.isAdmin()) {
	                Optional<DeductionGroup> optional = deductionGroupDao.findById(Integer.parseInt(requestMap.get("id")));
	                	if (optional.isPresent()) {

	                		deductionGroupDao.updateStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));
	                    return HrmsUtils.getResponeEntity("Deduction Group Status is updated Successfully", HttpStatus.OK);

	                } else {
	                    return HrmsUtils.getResponeEntity("Deduction Group id doesn't exist", HttpStatus.OK);
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
	public ResponseEntity<List<DeductionGroupWrapper>> getInfo(Integer id) {
		List<DeductionGroupWrapper> list = new ArrayList<DeductionGroupWrapper>();
        try {
            if (jwtFilter.isAdmin()) {
                Optional<DeductionGroup> optional = deductionGroupDao.findById(id);
                if (optional.isPresent()) {
                	return new ResponseEntity<List<DeductionGroupWrapper>>(deductionGroupDao.getDeductionGroupById(id), HttpStatus.OK);                	
                } 
                else {
                	return new ResponseEntity<List<DeductionGroupWrapper>>(list, HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<List<DeductionGroupWrapper>>(list, HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<DeductionGroupWrapper>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
        
	}

	@Override
	public ResponseEntity<String> updateInfo(Integer id, Map<String, String> requestMap) {
		 try {
	            if (jwtFilter.isAdmin()) {
	                Optional<DeductionGroup> optional = deductionGroupDao.findById(id);
	                	if (optional.isPresent()) {
	                		DeductionGroup department = new DeductionGroup();
	                		department.setName(requestMap.get("name"));
	                		department.setValue(requestMap.get("value"));
	                		department.setStatus(requestMap.get("status"));
	                		department.setId(id);
	                		deductionGroupDao.save(department);
	                    return HrmsUtils.getResponeEntity("Deduction Group Info is updated Successfully", HttpStatus.OK);

	                } else {
	                    return HrmsUtils.getResponeEntity("Deduction Group id doesn't exist", HttpStatus.OK);
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

//	            System.out.println("inside deduction group Service Impl");
			 DeductionGroup department = deductionGroupDao.findByName(requestMap.get("name"));
	                if (Objects.isNull(department)) {
	                	deductionGroupDao.save(getDeductionGroupFromMap(requestMap));
	                    return HrmsUtils.getResponeEntity("Successfully  Created.", HttpStatus.OK);
	                } else {
	                    return HrmsUtils.getResponeEntity("Deduction Group Name already exits.", HttpStatus.BAD_REQUEST);
	                }
	            
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

private DeductionGroup getDeductionGroupFromMap(Map<String, String> requestMap) {
	DeductionGroup department = new DeductionGroup();
	department.setName(requestMap.get("name"));
	department.setValue(requestMap.get("value"));
	department.setStatus("y");
    return department;
}

}
