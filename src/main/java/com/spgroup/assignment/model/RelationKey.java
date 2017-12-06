package com.spgroup.assignment.model;

import java.io.Serializable;

public class RelationKey implements Serializable {
	  
	private static final long serialVersionUID = 1L;

	protected Long relatingUserId;
	   
	protected Long relatedUserId;
	   
	public RelationKey(){
	}
	
	public RelationKey(Long relatingUserId,Long relatedUserId){
	    this.relatingUserId=relatingUserId;
		this.relatedUserId=relatedUserId;
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
	   public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((relatedUserId == null) ? 0 : relatedUserId.hashCode());
			result = prime * result + ((relatingUserId == null) ? 0 : relatingUserId.hashCode());
			return result;
		}
	   
	   @Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			RelationKey other = (RelationKey) obj;
			if (relatedUserId == null) {
				if (other.relatedUserId != null)
					return false;
			} else if (!relatedUserId.equals(other.relatedUserId))
				return false;
			if (relatingUserId == null) {
				if (other.relatingUserId != null)
					return false;
			} else if (!relatingUserId.equals(other.relatingUserId))
				return false;
			return true;
		}

	   @Override
	   public String toString() {
		   return "RelationKey [relatingUserId=" + relatingUserId + ", relatedUserId=" + relatedUserId + "]";
	   }

	}
