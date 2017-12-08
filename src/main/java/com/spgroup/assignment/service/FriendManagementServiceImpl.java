package com.spgroup.assignment.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spgroup.assignment.dto.Response;
import com.spgroup.assignment.exceptionhandling.FriendManagementApplicationException;

/*
 * @author sumit
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
 @Override
 public Map<String,Response> connectFriends(List<String> EmailIds) throws FriendManagementApplicationException{
	 Map<String,Response> respMap = new HashMap<String,Response>();
	 Response objResponse = new Response();
	 objResponse.setSuccess(usersRelationshipService.connectFriends(usersService.add(EmailIds)));
	 respMap.put("response",objResponse);
	 return respMap;
 }

 /*
  * function to return friend list of an email addresses
  */
 @Override
 public Map<String, Response> retrieveFriends(String email) throws FriendManagementApplicationException{
	 Map<String,Response> respMap = new HashMap<String,Response>();
	 Response objResponse = new Response();
	 
	 Set<String> allFriendsList = usersRelationshipService.retrieveFriends(email);
	 objResponse.setSuccess(true);
	 objResponse.setCount(String.valueOf(allFriendsList.size()));
	 objResponse.setFriends(new ArrayList<String>(allFriendsList));
	 respMap.put("response",objResponse);
	 return respMap;
 }

 /*
  * function to return Common friends of two users
  */
@Override
public Map<String, Response> commonFriends(List<String> EmailIds) throws FriendManagementApplicationException {
	Map<String,Response> respMap = new HashMap<String,Response>();
	Response objResponse = new Response();
	 
	Set<String> allFriendsFirstUser = usersRelationshipService.retrieveFriends(EmailIds.get(0));
	Set<String> allFriendsSecondUser = usersRelationshipService.retrieveFriends(EmailIds.get(1));
	allFriendsFirstUser.retainAll(allFriendsSecondUser);
	objResponse.setSuccess(true);
	objResponse.setCount(String.valueOf(allFriendsFirstUser.size()));
	objResponse.setFriends(new ArrayList<String>(allFriendsFirstUser));
	respMap.put("response",objResponse);
	return respMap;
}

@Override
public Map<String, Response> subscribe(String requestor, String target) throws FriendManagementApplicationException {
	 Map<String,Response> respMap = new HashMap<String,Response>();
	 Response objResponse = new Response();
	 List<String> orderedEmailIds= new ArrayList<String>();
	 orderedEmailIds.add(requestor);
	 orderedEmailIds.add(target);
	 objResponse.setSuccess(usersRelationshipService.subscribe(usersService.add(orderedEmailIds)));
	 respMap.put("response",objResponse);
	 return respMap;
}

@Override
public Map<String, Response> block(String requestor, String target) throws FriendManagementApplicationException {
	 logger.info("subscribe requestor:"+requestor+"To target:"+target);
	 Map<String,Response> respMap = new HashMap<String,Response>();
	 Response objResponse = new Response();
	 List<String> orderedEmailIds= new ArrayList<String>();
	 orderedEmailIds.add(requestor);
	 orderedEmailIds.add(target);
	 objResponse.setSuccess(usersRelationshipService.block(usersService.add(orderedEmailIds)));
	 respMap.put("response",objResponse);
	 return respMap;
}

@Override
public Map<String, Response> receive(String sender, String text) throws FriendManagementApplicationException {
	 logger.info("sender:"+sender+"Text:"+text);
	 Map<String,Response> respMap = new HashMap<String,Response>();
	 Response objResponse = new Response();
	 objResponse.setRecipients(new ArrayList<String>(usersRelationshipService.receive(sender,text)));
	 objResponse.setSuccess(true);
	 respMap.put("response",objResponse);
	 return respMap;
}
 
}
