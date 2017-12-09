package com.spgroup.assignment.controller;

import java.util.Map;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.spgroup.assignment.constants.CommonConstants;
import com.spgroup.assignment.dto.FriendListRequest;
import com.spgroup.assignment.dto.FriendRequest;
import com.spgroup.assignment.dto.ReceiveUpdateRequest;
import com.spgroup.assignment.dto.Response;
import com.spgroup.assignment.dto.SubscribeBlockRequest;
import com.spgroup.assignment.exceptionhandling.FriendManagementApplicationException;
import com.spgroup.assignment.service.FriendManagementService;
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
	public Response ConnectFriends(@Valid @RequestBody FriendRequest rq) throws FriendManagementApplicationException{
		logger.info("Connect Friends");
		Map<String,Response> reponseMap =  friendManagementService.connectFriends(rq.getFriends());
		return reponseMap == null ? null:reponseMap.get(CommonConstants.RESPONSE);
	}
	
	
	/*
	  Method to retrieve the friends list for an email address
	  Accepts Application/Json request.
	*/
	@PostMapping(path="/retrieve/friends/")
	public Response ConnectFriends(@Valid @RequestBody FriendListRequest rq) throws FriendManagementApplicationException{
		Map<String,Response> reponseMap = friendManagementService.retrieveFriends(rq.getEmail());
		return reponseMap == null ? null:reponseMap.get(CommonConstants.RESPONSE);
	}
	
	
	/*
	  Method to retrieve the friends list for an email address
	  Accepts Application/Json request.
	*/
	@PostMapping(path="/common/friends/")
	public Response CommonFriends(@Valid @RequestBody FriendRequest rq) throws FriendManagementApplicationException{
		Map<String,Response> reponseMap = friendManagementService.commonFriends(rq.getFriends());
		return reponseMap == null ? null:reponseMap.get(CommonConstants.RESPONSE);
	}
	
	
	/*
	  Method to subscribe to updates from an email address(different from friend connection)
	  Accepts Application/Json request.
	*/
	@PostMapping(path="/subscribe/")
	public Response SubscribeToUpdates(@Valid @RequestBody SubscribeBlockRequest rq) throws FriendManagementApplicationException{
		Map<String,Response> reponseMap =  friendManagementService.subscribe(rq.getRequestor(),rq.getTarget());
		return reponseMap == null ? null:reponseMap.get(CommonConstants.RESPONSE);
	}
	
	/*
	  Method to block UPDATES from an email address,It does not BLOCK already existing FRIEND connection.
	  Accepts Application/Json request.
	*/
	@PostMapping(path="/blockupdates/")
	public Response BlockUpdates(@Valid @RequestBody SubscribeBlockRequest rq) throws FriendManagementApplicationException{
		Map<String,Response> reponseMap =  friendManagementService.block(rq.getRequestor(),rq.getTarget());
		return reponseMap == null ? null:reponseMap.get(CommonConstants.RESPONSE);
	}
	
	/*
	  Method to retrieve all email addresses that can receive updates from an email address.
	  Accepts Application/Json request.
	*/
	@PostMapping(path="/receiveupdates/")
	public Response receiveUpdates(@Valid @RequestBody ReceiveUpdateRequest rq) throws FriendManagementApplicationException{
		Map<String,Response> reponseMap =  friendManagementService.receive(rq.getSender(),rq.getText());
		return reponseMap == null ? null:reponseMap.get(CommonConstants.RESPONSE);
	}
	
	
}
