package com.erp.blang.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.blang.dto.OtpDto;
import com.erp.blang.dto.RegistrationDto;
import com.erp.blang.manager.RegistrationManager;
import com.erp.blang.twilioconfig.TwilioAccountConfiguration;
import com.erp.blang.utilities.APIEndpoints;
import com.twilio.Twilio;

@RestController
@CrossOrigin
@RequestMapping(path = APIEndpoints.BASE_API_URL_V1 + "/registration")
public class RegistrationController {

	@Autowired
	RegistrationManager registrationManager;

	@Autowired
	TwilioAccountConfiguration twilioConfig;

	@CrossOrigin
	@PostMapping(value = "/emailOTP")
	public ResponseEntity<Object> emailOtp(@RequestBody RegistrationDto mail) throws MessagingException {
		return registrationManager.mailSender(mail);
	}

	@PostMapping(value = "/mobileOTP")
	public ResponseEntity<Object> mobileOtp(@RequestBody RegistrationDto mobileNo) {
		Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
		return registrationManager.mobileOtpSender(mobileNo);
	}

	@PostMapping(value = "/voiceOTP")
	public ResponseEntity<Object> voiceOtp(@RequestBody RegistrationDto mobileNo)  {
		Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
		return registrationManager.voiceOtpSender(mobileNo);
	}

	@PostMapping(value = "/verifyOTP")
	public ResponseEntity<Object> verifyOtp(@RequestBody OtpDto otp) {
		return registrationManager.otpVerify(otp);
	}

	@PostMapping(value = "/saveAdmin")
	public ResponseEntity<Object> saveAdmin(@RequestBody RegistrationDto regisDto) {
		return registrationManager.save(regisDto);
	}


}
