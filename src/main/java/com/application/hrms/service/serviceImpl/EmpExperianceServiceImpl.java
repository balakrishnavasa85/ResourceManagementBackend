package com.application.hrms.service.serviceImpl;
import com.application.hrms.JWT.JwtFilter;
import com.application.hrms.JWT.jwtUtil;
import com.application.hrms.POJO.EmpExperiance;
import com.application.hrms.POJO.User;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.dao.EmpExperianceDao;
import com.application.hrms.dao.UserDao;

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

	@Autowired
	UserDao userdao;

	@Override
	public ResponseEntity<List<EmpExperiance>> getEmpExperianceInfo(Integer id) {
		List<EmpExperiance> list = new ArrayList<EmpExperiance>();
        try {
//            if (jwtFilter.isAdmin()) {
                Optional<EmpExperiance> optional = relationDao.findById(id);
                if (optional.isPresent()) {
                	return new ResponseEntity<List<EmpExperiance>>(relationDao.getEmpExperianceById(id), HttpStatus.OK);                	
                } 
                else {
                	return new ResponseEntity<List<EmpExperiance>>(list, HttpStatus.UNAUTHORIZED);
                }
//            } else {
//                return new ResponseEntity<List<EmpExperianceWrapper>>(list, HttpStatus.UNAUTHORIZED);
//            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<EmpExperiance>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
        
	}

	@Override
	public ResponseEntity<String> updateInfo(Integer id, Map<String, String> requestMap) {
		 try {

				Optional<User> useri = userdao.findById(id);
	                Optional<EmpExperiance> optional = relationDao.findById(id);
	                	if (optional.isPresent()) {
	                		EmpExperiance relation = new EmpExperiance();
	                		relation.setCompanyName(requestMap.get("name"));
	                		relation.setExperiance(requestMap.get("experiance"));
	                		relation.setJoiningDate(requestMap.get("joiningdate"));
	                		relation.setReleaveDate(requestMap.get("releavedate"));
	                		relation.setUser(useri.get());
	                		relation.setId(id);
	    					relation.setFilepath(requestMap.get("filepath"));
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


	Optional<User> useri = userdao.findById(Integer.parseInt(requestMap.get("user")));
	EmpExperiance relation = new EmpExperiance();
	relation.setCompanyName(requestMap.get("companyname"));
	relation.setExperiance(requestMap.get("experiance"));
	relation.setJoiningDate(requestMap.get("joiningdate"));
	relation.setReleaveDate(requestMap.get("releavedate"));
	relation.setFilepath(requestMap.get("filepath"));
	relation.setUser(useri.get());	
    return relation;
}

@Override
public ResponseEntity<List<EmpExperiance>> getUserEmpExperianceInfo(Integer id) {
	List<EmpExperiance> list = new ArrayList<EmpExperiance>();
    try {
//        if (jwtFilter.isAdmin()) {
        	List<EmpExperiance> optional = relationDao.getEmpExperianceByUserId(id);
            if (optional.size() > 0) {
            	return new ResponseEntity<List<EmpExperiance>>(optional, HttpStatus.OK);                	
            } 
            else {
            	return new ResponseEntity<List<EmpExperiance>>(list, HttpStatus.OK);
            }
//        } else {
//            return new ResponseEntity<List<EmpExperianceWrapper>>(list, HttpStatus.UNAUTHORIZED);
//        }

    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return new ResponseEntity<List<EmpExperiance>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
}

}
