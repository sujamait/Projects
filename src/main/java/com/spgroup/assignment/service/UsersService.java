package com.spgroup.assignment.service;

import java.util.List;

import com.spgroup.assignment.exceptionhandling.FriendManagementApplicationException;

public interface UsersService {
  List<Long> add(List<String> EmailIds) throws FriendManagementApplicationException;
}
