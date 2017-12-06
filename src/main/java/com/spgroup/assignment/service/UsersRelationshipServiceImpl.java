package com.spgroup.assignment.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spgroup.assignment.constants.CommonConstants;
import com.spgroup.assignment.exceptionhandling.FriendManagementApplicationException;
import com.spgroup.assignment.model.UsersRelationship;
import com.spgroup.assignment.repository.UsersRelationshipRepository;

@Service
public class UsersRelationshipServiceImpl implements UsersRelationshipService {

	private static final Logger logger = LoggerFactory.getLogger(UsersRelationshipServiceImpl.class);
	   
	
	@Autowired
	private UsersRelationshipRepository usersRelationshiopRepository;

	
	@Override
	public boolean connectFriends(List<Long> ids) throws FriendManagementApplicationException{
 
		UsersRelationship usersRelationship=null;
		
		if(ids!=null && !ids.isEmpty()){
		  usersRelationship=usersRelationshiopRepository.findByUserId(ids.get(0),ids.get(1));
		}else{
		  return false;
		}
		 
	    if(usersRelationship==null){
	    		usersRelationship = new UsersRelationship();
	    		usersRelationship.setRelatingUserId(ids.get(0));
	    		usersRelationship.setRelatedUserId(ids.get(1));
	    		usersRelationship.setRelationType(CommonConstants.FRIEND);
	    		usersRelationship.setCreatedDt(new java.sql.Timestamp(System.currentTimeMillis()));
	    		usersRelationship.setUpdatedDt(new java.sql.Timestamp(System.currentTimeMillis()));
	    }else{
	    	    usersRelationship.setRelationType(CommonConstants.FRIEND);
	    	    usersRelationship.setUpdatedDt(new java.sql.Timestamp(System.currentTimeMillis()));
	    }
	    
	    usersRelationship=usersRelationshiopRepository.save(usersRelationship);
	    logger.info("ConnectFriends Saved!");
	    if(usersRelationship!=null)
		 return true;
	    else
	     return false;
		
	}

}
