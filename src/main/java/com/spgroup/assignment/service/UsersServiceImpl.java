package com.spgroup.assignment.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spgroup.assignment.exceptionhandling.FriendManagementApplicationException;
import com.spgroup.assignment.model.Users;
import com.spgroup.assignment.repository.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService {
	
	private static final Logger logger = LoggerFactory.getLogger(FriendManagementServiceImpl.class);
   
	@Autowired
	UsersRepository usersRepository;
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public List<Long> add(List<String> emailIds) throws FriendManagementApplicationException{
		 logger.info("Add Users");
		 List<Long> userIds = new ArrayList<Long>();
		 if( null != emailIds){
			  emailIds.stream().forEach((emailId) ->
			  {
				  Users user = usersRepository.findUserByEmailId(emailId.toLowerCase());
				  if(user == null){
					  user = new Users(emailId.toLowerCase());
					  user.setCreatedDt(new java.sql.Timestamp(System.currentTimeMillis()));
					  user.setUpdatedDt(new java.sql.Timestamp(System.currentTimeMillis()));
					  usersRepository.save(user);
				  }
				  userIds.add(user.getUserId());
			  });
		 }
		
		return userIds;
	}


}
