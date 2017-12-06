package com.spgroup.assignment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spgroup.assignment.model.Users;

@Repository
public interface UsersRepository extends CrudRepository<Users,Long>{

	Users findUserByEmailId(String emailId);

}
