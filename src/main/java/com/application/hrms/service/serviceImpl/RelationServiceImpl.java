package com.application.hrms.service.serviceImpl;
import com.application.hrms.JWT.JwtFilter;
import com.application.hrms.JWT.jwtUtil;
import com.application.hrms.POJO.Relation;
import com.application.hrms.constents.HrmsConstants;
import com.application.hrms.dao.RelationDao;

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

import com.application.hrms.service.RelationService;
import com.application.hrms.utils.HrmsUtils;
import com.application.hrms.wrapper.RelationWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RelationServiceImpl implements RelationService{

	@Autowired
    AuthenticationManager authenticationManager;
	
    @Autowired
    com.application.hrms.JWT.jwtUtil jwtUtil;

    @Autowired
    JwtFilter jwtFilter;
    
    @Autowired
    RelationDao relationDao;
    
	@Override
	public ResponseEntity<List<RelationWrapper>> getAllRelation() {

    	List<RelationWrapper> list = new ArrayList<RelationWrapper>();
        try {
            if (jwtFilter.isAdmin()) {
                return new ResponseEntity<List<RelationWrapper>>(relationDao.getAllRelations(), HttpStatus.OK);
            } else {
                return new ResponseEntity<List<RelationWrapper>>(list, HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<RelationWrapper>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> update(Map<String, String> requestMap) {
		   try {
	            if (jwtFilter.isAdmin()) {
	                Optional<Relation> optional = relationDao.findById(Integer.parseInt(requestMap.get("id")));
	                	if (optional.isPresent()) {

	                		relationDao.updateStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));
	                    return HrmsUtils.getResponeEntity("Relation Status is updated Successfully", HttpStatus.OK);

	                } else {
	                    return HrmsUtils.getResponeEntity("Relation id doesn't exist", HttpStatus.OK);
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
	public ResponseEntity<List<RelationWrapper>> getRelationInfo(Integer id) {
		List<RelationWrapper> list = new ArrayList<RelationWrapper>();
        try {
            if (jwtFilter.isAdmin()) {
                Optional<Relation> optional = relationDao.findById(id);
                if (optional.isPresent()) {
                	return new ResponseEntity<List<RelationWrapper>>(relationDao.getRelationById(id), HttpStatus.OK);                	
                } 
                else {
                	return new ResponseEntity<List<RelationWrapper>>(list, HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<List<RelationWrapper>>(list, HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<RelationWrapper>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
        
	}

	@Override
	public ResponseEntity<String> updateInfo(Integer id, Map<String, String> requestMap) {
		 try {
	            if (jwtFilter.isAdmin()) {
	                Optional<Relation> optional = relationDao.findById(id);
	                	if (optional.isPresent()) {
	                		Relation relation = new Relation();
	                		relation.setName(requestMap.get("name"));
	                		relation.setStatus(requestMap.get("status"));
	                		relation.setId(id);
	                		relationDao.save(relation);
	                    return HrmsUtils.getResponeEntity("Relation Info is updated Successfully", HttpStatus.OK);

	                } else {
	                    return HrmsUtils.getResponeEntity("Relation id doesn't exist", HttpStatus.OK);
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
	            	Relation Relation = relationDao.findByName(requestMap.get("name"));
	                if (Objects.isNull(Relation)) {
	                	relationDao.save(getRelationFromMap(requestMap));
	                    return HrmsUtils.getResponeEntity("Successfully  Created.", HttpStatus.OK);
	                } else {
	                    return HrmsUtils.getResponeEntity("Departmnt Name already exits.", HttpStatus.BAD_REQUEST);
	                }
	            
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return HrmsUtils.getResponeEntity(HrmsConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

private Relation getRelationFromMap(Map<String, String> requestMap) {
	Relation relation = new Relation();
	relation.setName(requestMap.get("name"));
	relation.setStatus("y");
	relation.setRelation(requestMap.get("relation"));
	relation.setUser(Integer.parseInt(requestMap.get("user")));
    return relation;
}

@Override
public ResponseEntity<List<RelationWrapper>> getUserRelationInfo(Integer id) {
	List<RelationWrapper> list = new ArrayList<RelationWrapper>();
    try {
        if (jwtFilter.isAdmin()) {
        	List<RelationWrapper> optional = relationDao.getRelationByUserId(id);
            if (optional.size() > 0) {
            	return new ResponseEntity<List<RelationWrapper>>(optional, HttpStatus.OK);                	
            } 
            else {
            	return new ResponseEntity<List<RelationWrapper>>(list, HttpStatus.OK);
            }
        } else {
            return new ResponseEntity<List<RelationWrapper>>(list, HttpStatus.UNAUTHORIZED);
        }

    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return new ResponseEntity<List<RelationWrapper>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
}

}
