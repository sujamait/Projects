package com.spgroup.assignment;

import com.spgroup.assignment.dto.FriendRequest;
import com.spgroup.assignment.repository.UsersRelationshipRepository;
import com.spgroup.assignment.repository.UsersRepository;
import com.spgroup.assignment.service.FriendManagementServiceImpl;
import com.spgroup.assignment.service.UsersRelationshipService;
import com.spgroup.assignment.service.UsersService;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT,classes = SpgroupApplication.class)
@AutoConfigureMockMvc
public class SpgroupApplicationIntegrationTest {
@Autowired
private MockMvc mockMvc;

@Autowired
private FriendManagementServiceImpl friendManagementService;
@Autowired
private UsersService usersService;
@Autowired
private UsersRelationshipService usersRelationshipService;
@Autowired
private UsersRepository usersRepository;
@Autowired
private UsersRelationshipRepository usersRelationshiopRepository;


@Autowired
ObjectMapper objectMapper;

@Test
public void SuccessTrueShouldReturnMessageFromService() throws Exception {
	
	FriendRequest freq = new FriendRequest();
	freq.setFriends(Arrays.asList("Test@gmail.com","Test@spgroup.com"));
	
	
	 this.mockMvc.perform(put("/connect/friends/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(freq)))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.success").
            		value("true"));
}
}