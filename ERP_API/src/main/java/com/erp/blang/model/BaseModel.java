package com.erp.blang.model;

import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

public class BaseModel {
	
	private String createdBy;
	private XMLGregorianCalendar createdAt;
	private String modifiedBy;
	private XMLGregorianCalendar modifiedAt;
	private Boolean isDeleted = false;
	private Date createdDate;
	private Date modifiedDate;

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public XMLGregorianCalendar getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(XMLGregorianCalendar createdAt) {
		this.createdAt = createdAt;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public XMLGregorianCalendar getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(XMLGregorianCalendar modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "BaseModel [createdBy=" + createdBy + ", createdAt=" + createdAt + ", modifiedBy=" + modifiedBy
				+ ", modifiedAt=" + modifiedAt + ", isDeleted=" + isDeleted + "]";
	}

}
