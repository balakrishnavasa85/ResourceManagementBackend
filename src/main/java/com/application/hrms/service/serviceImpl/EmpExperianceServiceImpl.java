package com.application.hrms.service.serviceImpl;
import com.application.hrms.JWT.JwtFilter;
import com.application.hrms.JWT.jwtUtil;
import com.application.hrms.POJO.EmpExperiance;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.dao.EmpExperianceDao;

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

import com.application.hrms.service.EmpExperianceService;
import com.application.hrms.utils.HrmsUtils;
import com.application.hrms.wrapper.EmpExperianceWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmpExperianceServiceImpl implements EmpExperianceService{

	@Autowired
    AuthenticationManager authenticationManager;
	
    @Autowired
    com.application.hrms.JWT.jwtUtil jwtUtil;

    @Autowired
    JwtFilter jwtFilter;
    
    @Autowired
    EmpExperianceDao relationDao;

	@Override
	public ResponseEntity<List<EmpExperianceWrapper>> getEmpExperianceInfo(Integer id) {
		List<EmpExperianceWrapper> list = new ArrayList<EmpExperianceWrapper>();
        try {
//            if (jwtFilter.isAdmin()) {
                Optional<EmpExperiance> optional = relationDao.findById(id);
                if (optional.isPresent()) {
                	return new ResponseEntity<List<EmpExperianceWrapper>>(relationDao.getEmpExperianceById(id), HttpStatus.OK);                	
                } 
                else {
                	return new ResponseEntity<List<EmpExperianceWrapper>>(list, HttpStatus.UNAUTHORIZED);
                }
//            } else {
//                return new ResponseEntity<List<EmpExperianceWrapper>>(list, HttpStatus.UNAUTHORIZED);
//            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<EmpExperianceWrapper>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
        
	}

	@Override
	public ResponseEntity<String> updateInfo(Integer id, Map<String, String> requestMap) {
		 try {
//	            if (jwtFilter.isAdmin()) {
	                Optional<EmpExperiance> optional = relationDao.findById(id);
	                	if (optional.isPresent()) {
	                		EmpExperiance relation = new EmpExperiance();
	                		relation.setCompanyName(requestMap.get("name"));
	                		relation.setExperiance(requestMap.get("experiance"));
	                		relation.setJoiningDate(requestMap.get("joiningdate"));
	                		relation.setReleaveDate(requestMap.get("releavedate"));
	                		relation.setUser(Integer.parseInt(requestMap.get("user")));
	                		relation.setId(id);
	                		relationDao.save(relation);
	                    return HrmsUtils.getResponeEntity("Relation Info is updated Successfully", HttpStatus.OK);

	                } else {
	                    return HrmsUtils.getResponeEntity("Relation id doesn't exist", HttpStatus.OK);
	                }
//	            } else {
//	                return HrmsUtils.getResponeEntity(HrmsConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
//	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> create(Map<String, String> requestMap) {
		 try {
			 EmpExperiance Relation = relationDao.findByName(requestMap.get("companyname"));
	                if (Objects.isNull(Relation)) {
	                	relationDao.save(getEmpExperianceFromMap(requestMap));
	                    return HrmsUtils.getResponeEntity("Successfully  Created.", HttpStatus.OK);
	                } else {
	                    return HrmsUtils.getResponeEntity("Departmnt Name already exits.", HttpStatus.BAD_REQUEST);
	                }
	            
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

private EmpExperiance getEmpExperianceFromMap(Map<String, String> requestMap) {
	EmpExperiance relation = new EmpExperiance();
	relation.setCompanyName(requestMap.get("companyname"));
	relation.setExperiance(requestMap.get("experiance"));
	relation.setJoiningDate(requestMap.get("joiningdate"));
	relation.setReleaveDate(requestMap.get("releavedate"));
	relation.setUser(Integer.parseInt(requestMap.get("user")));	
    return relation;
}

@Override
public ResponseEntity<List<EmpExperianceWrapper>> getUserEmpExperianceInfo(Integer id) {
	List<EmpExperianceWrapper> list = new ArrayList<EmpExperianceWrapper>();
    try {
//        if (jwtFilter.isAdmin()) {
        	List<EmpExperianceWrapper> optional = relationDao.getEmpExperianceByUserId(id);
            if (optional.size() > 0) {
            	return new ResponseEntity<List<EmpExperianceWrapper>>(optional, HttpStatus.OK);                	
            } 
            else {
            	return new ResponseEntity<List<EmpExperianceWrapper>>(list, HttpStatus.OK);
            }
//        } else {
//            return new ResponseEntity<List<EmpExperianceWrapper>>(list, HttpStatus.UNAUTHORIZED);
//        }

    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return new ResponseEntity<List<EmpExperianceWrapper>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
}

}
