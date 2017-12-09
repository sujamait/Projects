package com.spgroup.assignment.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.spgroup.assignment.model.RelationKey;
import com.spgroup.assignment.model.UsersRelationship;

@Repository
public interface UsersRelationshipRepository extends CrudRepository<UsersRelationship,RelationKey>{
	List<UsersRelationship> findByRelatingUserIdOrRelatedUserIdAndRelationType(Long relatingUserId, Long relatedUserId,String relationType);
	List<UsersRelationship> findByUserId(Long relatingUserId,Long relatedUserId,String relationType);
	UsersRelationship findSubscriberByUserId(Long relatingUserId,Long relatedUserId,String relationType);
	List<UsersRelationship> findByRelatedUserId(Long userId);
}
