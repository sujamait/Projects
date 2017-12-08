package com.spgroup.assignment.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class SubscribeBlockRequest {
  
  @NotNull
  @Size(max = 254)
  @Pattern(regexp = "[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+")
  private String requestor;
	
  @NotNull
  @Size(max = 254)
  @Pattern(regexp = "[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+")
  private String target;
  
  public String getRequestor() {
	return requestor;
  }
  public void setRequestor(String requestor) {
	this.requestor = requestor;
  }
  public String getTarget() {
	return target;
  }
  public void setTarget(String target) {
	this.target = target;
  }
}
