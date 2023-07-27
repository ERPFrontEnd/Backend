package com.erp.blang.Authorization_Authentication;

import java.util.HashSet;
import java.util.Set;

import com.erp.blang.model.Roles;


public class LoginResponse {

	private String id;
	
	private String email;
	
//	private String password;
	
	private Set<Roles> roleName=new HashSet<>();
	
	private String jwtToken;

	public LoginResponse() {
	
	}

	public LoginResponse(String id, String email, Set<Roles> roleName, String jwtToken) {
		super();
		this.id = id;
		this.email = email;
//		this.password = password;
		this.roleName = roleName;
		this.jwtToken = jwtToken;
	}

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

//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}

	public Set<Roles> getRoleName() {
		return roleName;
	}

	public void setRoleName(Set<Roles> roleName) {
		this.roleName = roleName;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	
	
}
