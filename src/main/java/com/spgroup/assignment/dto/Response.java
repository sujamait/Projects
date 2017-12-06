package com.spgroup.assignment.dto;

public class Response<T> {
    boolean success;
    
	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
