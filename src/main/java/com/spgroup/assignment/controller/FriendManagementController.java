package com.spgroup.assignment.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spgroup.assignment.dto.FriendRequest;
import com.spgroup.assignment.dto.Response;
import com.spgroup.assignment.exceptionhandling.FriendManagementApplicationException;
import com.spgroup.assignment.service.FriendManagementService;
import com.spgroup.assignment.service.FriendManagementServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

/*
 * @author sumit
 * FriendManagementController takes all restful requests to cover all 
 * User Stories
 */

@RestController
public class FriendManagementController {
	private static final Logger logger = LoggerFactory.getLogger(FriendManagementController.class);
	
	@Autowired
	FriendManagementService friendManagementService;
	
	/*
	  Method to create a friend connection between two email addresses
	  Accepts Application/Json request.
	 */
	@PutMapping(path="/connect/friends/")
	public Response<String> ConnectFriends(@Valid @RequestBody FriendRequest rq) throws FriendManagementApplicationException{
		boolean status =  friendManagementService.connectFriends(rq.getFriends());
		Response<String> response = new Response<String>();
		response.setSuccess(status);
		return response;
	}
}
