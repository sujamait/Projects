package com.spgroup.assignment.service;

import java.util.List;

import com.spgroup.assignment.exceptionhandling.FriendManagementApplicationException;

public interface UsersRelationshipService {
  boolean connectFriends(List<Long> ids) throws FriendManagementApplicationException;
  
}
