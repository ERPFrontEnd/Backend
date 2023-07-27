package com.erp.blang.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class Users extends BaseModel{

	@Id
	private String id;

	private String email;
	
	private String mobileNumber;

	private String userFirstName;

	private String userLastName;

	private String userdisplayName;
	
	private Set<String> userPreferences;
	
	private boolean initialLoginSetUp;

	private String password;

	private Set<Roles> roleName = new HashSet<>();


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

	public Set<Roles> getRoleName() {
		return roleName;
	}

	public void setRoleName(Set<Roles> roleName) {
		this.roleName = roleName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Set<String> getUserPreferences() {
		return userPreferences;
	}

	public void setUserPreferences(Set<String> userPreferences) {
		this.userPreferences = userPreferences;
	}

	public boolean getInitialLoginSetUp() {
		return initialLoginSetUp;
	}

	public void setInitialLoginSetUp(boolean initialLoginSetUp) {
		this.initialLoginSetUp = initialLoginSetUp;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", email=" + email + ", mobileNumber=" + mobileNumber + ", userFirstName="
				+ userFirstName + ", userLastName=" + userLastName + ", userdisplayName=" + userdisplayName
				+ ", userPreferences=" + userPreferences + ", initialLoginSetUp=" + initialLoginSetUp + ", password="
				+ password + ", roleName=" + roleName + "]";
	}	
	
	
	
}
