package com.spgroup.assignment.service;

import java.util.List;
import java.util.Map;

import com.spgroup.assignment.dto.Response;
import com.spgroup.assignment.exceptionhandling.FriendManagementApplicationException;

public interface FriendManagementService {
	
	Map<String,Response> connectFriends(List<String> EmailId) throws FriendManagementApplicationException;

	Map<String,Response> retrieveFriends(String email) throws FriendManagementApplicationException;

	Map<String, Response> commonFriends(List<String> friends) throws FriendManagementApplicationException;

	Map<String, Response> subscribe(String requestor, String target) throws FriendManagementApplicationException;

	Map<String, Response> block(String requestor, String target) throws FriendManagementApplicationException;

	Map<String, Response> receive(String sender, String text) throws FriendManagementApplicationException;

}
