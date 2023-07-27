	package com.erp.blang.manager;

import javax.mail.MessagingException;

import org.springframework.http.ResponseEntity;

import com.erp.blang.core.AbstractService;
import com.erp.blang.dto.OtpDto;
import com.erp.blang.dto.RegistrationDto;

public interface RegistrationManager extends AbstractService<RegistrationDto> {

	public ResponseEntity<Object> mailSender(RegistrationDto mail) throws MessagingException;

	public ResponseEntity<Object> otpVerify(OtpDto otp);

	public ResponseEntity<Object> mobileOtpSender(RegistrationDto mobileNumber);

	public ResponseEntity<Object> voiceOtpSender(RegistrationDto mobileNo);

}
