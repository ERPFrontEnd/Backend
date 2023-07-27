package com.erp.blang.twilioconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("twilio")
public class TwilioAccountConfiguration {

	private String accountSid;

	private String authToken;

	private String trailNumber = "+14302456566";

	public String getAccountSid() {
		return accountSid;
	}

	public void setAccountSid(String accountSid) {
		this.accountSid = accountSid;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getTrailNumber() {
		return trailNumber;
	}

	public void setTrailNumber(String trailNumber) {
		this.trailNumber = trailNumber;
	}

}
