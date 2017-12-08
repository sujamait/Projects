package com.spgroup.assignment.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ReceiveUpdateRequest {
	

  @NotNull
  @Size(max = 254)
  @Pattern(regexp = "[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+")
  private String sender;
  
  private String text;

  public String getSender() {
		return sender;
  }

  public void setSender(String sender) {
	    this.sender = sender;
  }

  public String getText() {
	   return text;
  }

  public void setText(String text) {
	  this.text = text;
  }
}
