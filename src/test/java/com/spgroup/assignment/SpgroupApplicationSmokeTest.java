package com.spgroup.assignment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import com.spgroup.assignment.controller.FriendManagementController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpgroupApplicationSmokeTest {

	@Autowired
    private FriendManagementController controller;
	
	@Test
	public void contextLoads() {
		//verify if controller is created
		assertThat(controller).isNotNull();
	}

}
