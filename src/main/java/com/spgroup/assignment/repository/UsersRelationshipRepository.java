package com.spgroup.assignment.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spgroup.assignment.exceptionhandling.FriendManagementApplicationException;
import com.spgroup.assignment.model.RelationKey;
import com.spgroup.assignment.model.UsersRelationship;

@Repository
public interface UsersRelationshipRepository extends CrudRepository<UsersRelationship,RelationKey>{

	UsersRelationship findByRelatingUserOrRelatedUser(Long relatingUserId, Long relatedUserId);
	UsersRelationship findByUserId(Long relatingUserId,Long relatedUserId);
}
