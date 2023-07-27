package com.erp.blang.Authorization_Authentication;

public class LoginRequest {

	private String userName;
	
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setEmail(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

