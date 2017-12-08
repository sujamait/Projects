package com.spgroup.assignment.service;

import java.util.List;
import java.util.Set;

import com.spgroup.assignment.exceptionhandling.FriendManagementApplicationException;

public interface UsersRelationshipService {
	
  boolean connectFriends(List<Long> ids) throws FriendManagementApplicationException;
  
  Set<String> retrieveFriends(String emailId) throws FriendManagementApplicationException;
  
  boolean subscribe(List<Long> add) throws FriendManagementApplicationException;
  
  boolean block(List<Long> add) throws FriendManagementApplicationException;
  
  Set<String> receive(String sender, String text) throws FriendManagementApplicationException;
  
}
