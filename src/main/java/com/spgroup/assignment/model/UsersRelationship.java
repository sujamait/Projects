package com.spgroup.assignment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity(name="users_relationship")
@IdClass(RelationKey.class)
@NamedQuery(name = "UsersRelationship.findByUserId",
query = "select u from users_relationship u where ((u.relatingUserId = ?1 and u.relatedUserId = ?2) or (u.relatingUserId = ?2 and u.relatedUserId = ?1))")
public class UsersRelationship extends BaseDomain {

 	
 @Transient	
 private static final long serialVersionUID = 1L;

 @Id private @Column(name="relating_user_id", nullable = false) Long relatingUserId;
 @Id private @Column(name="related_user_id", nullable = false) Long relatedUserId;
 
 @Column(name="type", nullable = false)
 private String relationType;
 
 @OneToOne(fetch = FetchType.LAZY)
 @JoinColumn(name="relating_user_id", referencedColumnName="id", insertable = false, updatable = false)
 private Users relatingUser;
 
 
 @OneToOne(fetch = FetchType.LAZY)
 @JoinColumn(name="related_user_id", referencedColumnName="id", insertable = false, updatable = false) 
 private Users relatedUser;
 
 
 public UsersRelationship(){
	 
 }
	
 public UsersRelationship(Long relatingUserId,Long relatedUserId,String relationType){
	 this.relatingUserId=relatingUserId;
	 this.relatedUserId=relatedUserId;
	 this.relationType=relationType;
 }



public String getRelationType() {
	return relationType;
}

public void setRelationType(String relationType) {
	this.relationType = relationType;
}

public Users getRelatingUser() {
	return relatingUser;
}

public void setRelatingUser(Users relatingUser) {
	this.relatingUser = relatingUser;
}

public Users getRelatedUser() {
	return relatedUser;
}

public void setRelatedUser(Users relatedUser) {
	this.relatedUser = relatedUser;
}

public Long getRelatingUserId() {
	return relatingUserId;
}

public void setRelatingUserId(Long relatingUserId) {
	this.relatingUserId = relatingUserId;
}

public Long getRelatedUserId() {
	return relatedUserId;
}

public void setRelatedUserId(Long relatedUserId) {
	this.relatedUserId = relatedUserId;
}

@Override
public String toString() {
	return "UsersRelationship [relatingUserId=" + relatingUserId + ", relatedUserId=" + relatedUserId
			+ ", relationType=" + relationType + ", relatingUser=" + relatingUser + ", relatedUser=" + relatedUser
			+ "]";
}
	
}
