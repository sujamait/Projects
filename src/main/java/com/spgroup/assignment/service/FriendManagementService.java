package com.spgroup.assignment.service;

import java.util.List;

import com.spgroup.assignment.exceptionhandling.FriendManagementApplicationException;

public interface FriendManagementService {
	boolean connectFriends(List<String> EmailId) throws FriendManagementApplicationException;
}
