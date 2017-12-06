package com.spgroup.assignment.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spgroup.assignment.exceptionhandling.FriendManagementApplicationException;
import com.spgroup.assignment.repository.UsersRelationshipRepository;
import com.spgroup.assignment.repository.UsersRepository;

/*
 * @author sumi
 * Friend Management service to manage all the User Stories
*/

@Service
public class FriendManagementServiceImpl implements FriendManagementService{
	
 private static final Logger logger = LoggerFactory.getLogger(FriendManagementServiceImpl.class);
	
	
 @Autowired
 private UsersService usersService;
 
 @Autowired
 private UsersRelationshipService usersRelationshipService;
 
 /*
  * function to create a friend connection between two email addresses
  */
 @Transactional
 public boolean connectFriends(List<String> EmailIds) throws FriendManagementApplicationException{
	 return usersRelationshipService.connectFriends(usersService.add(EmailIds));
 }
 
}
