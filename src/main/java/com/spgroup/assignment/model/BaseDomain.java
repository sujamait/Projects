package com.spgroup.assignment.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class BaseDomain implements Serializable{
	
	@Transient
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_dt")
    private Date createdDt;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_dt")
    private Date updatedDt;

	public Date getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}

	public Date getUpdatedDt() {
		return updatedDt;
	}

	public void setUpdatedDt(Date updatedDt) {
		this.updatedDt = updatedDt;
	}
}
