package com.spgroup.assignment.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spgroup.assignment.constants.CommonConstants;
import com.spgroup.assignment.exceptionhandling.FriendManagementApplicationException;
import com.spgroup.assignment.model.Users;
import com.spgroup.assignment.model.UsersRelationship;
import com.spgroup.assignment.repository.UsersRelationshipRepository;
import com.spgroup.assignment.repository.UsersRepository;

@Service
public class UsersRelationshipServiceImpl implements UsersRelationshipService {
	private static final Logger logger = LoggerFactory.getLogger(UsersRelationshipServiceImpl.class);
	
	@Autowired
	private UsersRelationshipRepository usersRelationshiopRepository;
	
	@Autowired
	UsersRepository usersRepository;
	
	@Value("${email.pattern}")
	private String emailRegEx;
	
	@Override
	public boolean connectFriends(List<Long> ids) throws FriendManagementApplicationException{
		return relateUserRelationShip(ids, CommonConstants.FRIEND);
	}


	@Override
	public Set<String> retrieveFriends(String emailId) throws FriendManagementApplicationException {
		Set<String> friendList = new HashSet<String>();
		Users user=usersRepository.findUserByEmailId(emailId.toLowerCase());
		if(user!=null){
		 List<UsersRelationship> usersRelationshipList = usersRelationshiopRepository.findByRelatingUserIdOrRelatedUserIdAndRelationType(user.getUserId(),user.getUserId(),CommonConstants.FRIEND);
		 if(usersRelationshipList!=null){
			 for(UsersRelationship usersRelationship:usersRelationshipList){
				 if(usersRelationship.getRelatingUser()!=null && usersRelationship.getRelatedUser()!=null){
					 friendList.add(usersRelationship.getRelatingUser().getEmailId()); 
					 friendList.add(usersRelationship.getRelatedUser().getEmailId());
				 }
			 }
			 friendList.remove(emailId);
		 }
		}
		return friendList;
	}


	@Override
	public boolean subscribe(List<Long> ids) throws FriendManagementApplicationException {
		return relateUserRelationShip(ids, CommonConstants.SUBSCRIBE);
	}


	@Transactional(propagation = Propagation.REQUIRED)
	private boolean relateUserRelationShip(List<Long> ids, String typeOfRelation) {
		UsersRelationship usersRelationship=null;
		UsersRelationship usersRelationshipSaved=null;
		if(ids!=null && !ids.isEmpty()){
			if(CommonConstants.FRIEND.equals(typeOfRelation)){
		       List<UsersRelationship> usersRelationshipList=usersRelationshiopRepository.findByUserId(ids.get(0),ids.get(1),typeOfRelation);
			   if(usersRelationshipList!=null && usersRelationshipList.size()>0){
				   usersRelationship=usersRelationshipList.get(0);
				   for(UsersRelationship objUsersRelationship:usersRelationshipList){
					   if(CommonConstants.FRIEND.equals(objUsersRelationship.getRelationType())){
						   usersRelationship=objUsersRelationship;
						   break;
					   }
				   }
			   }
			}else if(CommonConstants.SUBSCRIBE.equals(typeOfRelation)){
				usersRelationship = usersRelationshiopRepository.findSubscriberByUserId(ids.get(0),ids.get(1),typeOfRelation);
			}else{
				logger.info("Invalid Relation Type");
			}
		}else{
		  return false;
		}
		
	    if(usersRelationship==null){
	    	  usersRelationshipSaved=populateAndSaveUserRelationship(ids,typeOfRelation);
	    }else{
	    	    //If blocked no new friends connection can be added,although can re subscribe
	    	    if(CommonConstants.BLOCK.equals(usersRelationship.getRelationType()) &&
	    	    		CommonConstants.FRIEND.equals(typeOfRelation)){
	    	    	return false;
	    	    }
	    	    if(CommonConstants.FRIEND.equals(typeOfRelation)){
	    	    	usersRelationship.setUpdatedDt(new java.sql.Timestamp(System.currentTimeMillis()));
	    	    	usersRelationshiopRepository.save(usersRelationship);
	    	    	return true;
	    	    }
	    		
	    	    usersRelationshipSaved = populateAndSaveUserRelationship(ids,typeOfRelation);
	    	    usersRelationshipSaved.setCreatedDt(usersRelationship.getCreatedDt());
	    	    usersRelationshiopRepository.delete(usersRelationship);
	    }
	    return usersRelationshipSaved!=null ? true:false;
	}


	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean block(List<Long> ids) throws FriendManagementApplicationException {
		UsersRelationship usersRelationship = null;
		UsersRelationship usersRelationshipAsFriend = null;
		UsersRelationship usersRelationshipAsSubscriber = null;
		//Check if Users are connected as FRIENDs
		List<UsersRelationship> usersRelationshipAsFriendList=usersRelationshiopRepository.findByUserId(ids.get(0),ids.get(1),CommonConstants.FRIEND);
		if(usersRelationshipAsFriendList!=null && usersRelationshipAsFriendList.size()>0){
			   usersRelationshipAsFriend=usersRelationshipAsFriendList.get(0);
			   for(UsersRelationship objUsersRelationship:usersRelationshipAsFriendList){
				   if(CommonConstants.FRIEND.equals(objUsersRelationship.getRelationType())){
					   usersRelationshipAsFriend=objUsersRelationship;
					   break;
				   }
			   }
		 }
		
		usersRelationshipAsSubscriber=usersRelationshiopRepository.findSubscriberByUserId(ids.get(0),ids.get(1),CommonConstants.SUBSCRIBE);
		
		//Not connected as FRIENDs
		if((usersRelationshipAsFriend==null) || (usersRelationshipAsFriend!=null && CommonConstants.BLOCK.equals(usersRelationshipAsFriend.getRelationType())))
		{
		 //Block the Subscribe relation,If no subscribe relation present then Create New record with type BLOCK.
		 if(usersRelationshipAsFriend==null && usersRelationshipAsSubscriber==null){ //When no relation between users
			  usersRelationship = populateAndSaveUserRelationship(ids,CommonConstants.BLOCK);
		 }else{
			  
			  usersRelationship = populateAndSaveUserRelationship(ids,CommonConstants.BLOCK);
			  usersRelationship.setCreatedDt(usersRelationshipAsSubscriber.getCreatedDt());
			  usersRelationshiopRepository.delete(usersRelationshipAsSubscriber);
		 }
		}else{//Already Connected as FRIENDs
			if(usersRelationshipAsSubscriber==null){
			  usersRelationship = populateAndSaveUserRelationship(ids,CommonConstants.BLOCK);
		}else{
			 usersRelationship = populateAndSaveUserRelationship(ids,CommonConstants.BLOCK);
			 usersRelationship.setCreatedDt(usersRelationshipAsSubscriber.getCreatedDt());
			 usersRelationshiopRepository.delete(usersRelationshipAsSubscriber);
		}
		}
		return usersRelationship!=null ? true:false;
	}

    @Transactional(propagation = Propagation.REQUIRES_NEW)
	private UsersRelationship populateAndSaveUserRelationship(List<Long> ids,String relationType) {
		 UsersRelationship objUsersRelationship=new UsersRelationship();;
		 objUsersRelationship.setRelatingUserId(ids.get(0));
		 objUsersRelationship.setRelatedUserId(ids.get(1));
		 objUsersRelationship.setRelationType(relationType);
		 objUsersRelationship.setUpdatedDt(new java.sql.Timestamp(System.currentTimeMillis()));
		 objUsersRelationship.setCreatedDt(new java.sql.Timestamp(System.currentTimeMillis()));
		 objUsersRelationship=usersRelationshiopRepository.save(objUsersRelationship);
		 return objUsersRelationship;
	}


	@Override
	public Set<String> receive(String sender, String text) throws FriendManagementApplicationException {
		
		Set<String> recipientsList = new HashSet<String>();
		Set<String> textRecipientsList = null;
		if(text!=null){
			Matcher m = Pattern.compile(emailRegEx).matcher(text);
			textRecipientsList =  new HashSet<String>();
		    while (m.find()) {
		    	textRecipientsList.add(m.group().toString());
		    }
		}
		
		Users user=usersRepository.findUserByEmailId(sender.toLowerCase());
		
		if(user!=null){
			List<UsersRelationship> usersRelationshipList = usersRelationshiopRepository.
					findByRelatedUserId(user.getUserId());
			if(usersRelationshipList!=null)
			for(UsersRelationship usersRelationship:usersRelationshipList){
				if(usersRelationship.getRelationType().equals(CommonConstants.BLOCK)){
					if(textRecipientsList !=null && textRecipientsList.contains(usersRelationship.getRelatingUser().getEmailId())){
						textRecipientsList.remove(usersRelationship.getRelatingUser().getEmailId());
					}
					continue;
				}
				
			    recipientsList.add(usersRelationship.getRelatingUser().getEmailId());
		    }
		}	
		recipientsList.addAll(textRecipientsList);
		return recipientsList;
	}

}
