package com.spgroup.assignment.dto;

import java.util.List;
import javax.validation.constraints.Size;
import com.spgroup.assignment.annotation.EmailCollection;

public class FriendRequest {

	@Size(min = 2, max = 2)
	@EmailCollection
	private List<String> friends;
	
	public List<String> getFriends() {
		return friends;
	}

	public void setFriends(List<String> friends) {
		this.friends = friends;
	}
	
}
