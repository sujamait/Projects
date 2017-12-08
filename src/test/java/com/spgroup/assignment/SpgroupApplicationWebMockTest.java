package com.spgroup.assignment;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spgroup.assignment.controller.FriendManagementController;
import com.spgroup.assignment.dto.FriendRequest;
import com.spgroup.assignment.dto.Response;
import com.spgroup.assignment.repository.UsersRelationshipRepository;
import com.spgroup.assignment.repository.UsersRepository;
import com.spgroup.assignment.service.FriendManagementServiceImpl;
import com.spgroup.assignment.service.UsersRelationshipService;
import com.spgroup.assignment.service.UsersService;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(FriendManagementController.class)
public class SpgroupApplicationWebMockTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private FriendManagementServiceImpl friendManagementService;
    @MockBean
    private UsersService usersService;
    @MockBean
    private UsersRelationshipService usersRelationshipService;
    @MockBean
    private UsersRepository usersRepository;
    @MockBean
    private UsersRelationshipRepository usersRelationshiopRepository;

   
    @Autowired
    ObjectMapper objectMapper;
    
    @Test
    public void ShouldReturnMethodArgumentNotValidExceptionBadRequest() throws Exception {
    	
    	FriendRequest freq = new FriendRequest();
    	//Friends request json with Length > 2
    	freq.setFriends(Arrays.asList("Test@gmail.com","Test@spgroup.com","Test@gmail.com"));
    	
    	this.mockMvc.perform(put("/connect/friends/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(freq)))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    public void CheckEmailAddressValidationBadRequest() throws Exception {
    	FriendRequest freq = new FriendRequest();
    	//Friends request json with invalid email
    	freq.setFriends(Arrays.asList("Testgmail.com","Testspgroup.com"));
    	
    	this.mockMvc.perform(put("/connect/friends/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(freq)))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void SuccessTrueShouldReturnMessageFromService() throws Exception {
    	
    	FriendRequest freq = new FriendRequest();
    	freq.setFriends(Arrays.asList("Hum@gmail.com","Charles@spgroup.com"));
    	
    	Map<String,Response> responseMp = new HashMap<String,Response>();
    	Response response= new Response();
    	response.setSuccess(true);
    	responseMp.put("response", response);
    	 given(friendManagementService.connectFriends(Arrays.asList("Hum@gmail.com","Charles@spgroup.com")))
    	 .willReturn(responseMp);

    	
    	 this.mockMvc.perform(put("/connect/friends/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(freq)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").
                		value("true"));
    }
}
