package com.erp.blang.dto;

import java.util.HashSet;
import java.util.Set;

import javax.xml.datatype.XMLGregorianCalendar;

public class UsersDTO {

	private String id;

	private String email;
	
	private String mobileNumber;

	private String userFirstName;

	private String userLastName;

	private String userdisplayName;
	
	private Set<String> userPreferences;
	
	private String initialLoginSetUp;

	private String password;

	private Set<String> roleName = new HashSet<>();
	
	private String createdBy;
	private XMLGregorianCalendar createdAt;
	private String modifiedBy;
	private XMLGregorianCalendar modifiedAt;
	private Boolean isDeleted = false;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserdisplayName() {
		return userdisplayName;
	}

	public void setUserdisplayName(String userdisplayName) {
		this.userdisplayName = userdisplayName;
	}

//	public Set<Roles> getRoleName() {
//		return roleName;
//	}
//
//	public void setRoleName(Set<Roles> roleName) {
//		this.roleName = roleName;
//	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Set<String> getRoleName() {
		return roleName;
	}

	public void setRoleName(Set<String> roleName) {
		this.roleName = roleName;
	}

	public Set<String> getUserPreferences() {
		return userPreferences;
	}

	public void setUserPreferences(Set<String> userPreferences) {
		this.userPreferences = userPreferences;
	}

	public String getInitialLoginSetUp() {
		return initialLoginSetUp;
	}

	public void setInitialLoginSetUp(String initialLoginSetUp) {
		this.initialLoginSetUp = initialLoginSetUp;
	}
}
