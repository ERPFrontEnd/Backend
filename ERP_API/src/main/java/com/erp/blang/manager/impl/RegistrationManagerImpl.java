package com.erp.blang.manager.impl;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.erp.blang.Authorization_Authentication.JWTUtility;
import com.erp.blang.Authorization_Authentication.RequestFilter;
import com.erp.blang.dto.OtpDto;
import com.erp.blang.dto.RegistrationDto;
import com.erp.blang.filter.SearchRequest;
import com.erp.blang.manager.RegistrationManager;
import com.erp.blang.model.Registration;
import com.erp.blang.model.Roles;
import com.erp.blang.model.Users;
import com.erp.blang.repository.RegistrationRepository;
import com.erp.blang.repository.RoleRepository;
import com.erp.blang.repository.UserRepository;
import com.erp.blang.responsehandle.ResponseHandler;
import com.erp.blang.twilioconfig.TwilioAccountConfiguration;
import com.erp.blang.utilities.DateUtils;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.twiml.VoiceResponse;
import com.twilio.twiml.voice.Say;
import com.twilio.twiml.voice.Say.Language;
import com.twilio.type.PhoneNumber;


@Service
public class RegistrationManagerImpl implements RegistrationManager {

	private static final String CHARACTERS = "0123456789";
	private static final int OTP_LENGTH = 6;
	String otpgenerated;

	private Random random = new SecureRandom();

	@Autowired
	RegistrationRepository registrationRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	RoleRepository roleRepo;

	@Autowired
	JavaMailSender mailSender1;

//	@Autowired
//	CompanyRepo companyRepo;
	
	@Autowired PasswordEncoder passwordEncoder;

	@Autowired
	TwilioAccountConfiguration twilioConfig;

	/*
	 * 
	 * FOR Sending OTP to Mail
	 * 
	 **/
	@Override
	public ResponseEntity<Object> mailSender(RegistrationDto mail) throws MessagingException {

		
		Registration mailInDb = registrationRepo.findByEmail(mail.getEmail());

		if (mailInDb != null) {
			return ResponseHandler.responseBuilder(HttpStatus.ALREADY_REPORTED, "Mail already Exists");
		} else {
			MimeMessage mimeMessage = mailSender1.createMimeMessage();
			MimeMessageHelper mimeMessageHelper;
			this.otpgenerated = generateOTP();
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setTo(mail.getEmail());
			mimeMessageHelper.setFrom("hariofficial137@gmail.com");
			mimeMessageHelper.setSubject("OTP for Versatile ERP");
			mimeMessageHelper.setText("This is OTP :" + otpgenerated, true);
			mailSender1.send(mimeMessage);
			return ResponseHandler.responseBuilder(HttpStatus.OK, "Your OTP: " + this.otpgenerated);
		}
	}

	/*
	 * 
	 * FOR GENERATING OTP
	 * 
	 */
	public String generateOTP() {
		StringBuilder otp = new StringBuilder(OTP_LENGTH);
		for (int i = 0; i < OTP_LENGTH; i++) {
			int randomIndex = random.nextInt(CHARACTERS.length());
			otp.append(CHARACTERS.charAt(randomIndex));
		}
		return otp.toString();
	}

	


	/*
	 * 
	 * FOR VERIFYING OTP
	 * 
	 */
	@Override
	public ResponseEntity<Object> otpVerify(OtpDto otp) {
		if (otpgenerated.equals(otp.getOtp())) {
			otp.setOtpStatus(true);
			return ResponseHandler.responseBuilder(HttpStatus.ACCEPTED, otp.getOtpStatus());
		} else {
			otp.setOtpStatus(false);
			return ResponseHandler.responseBuilder(HttpStatus.NOT_ACCEPTABLE, otp.getOtpStatus());
		}
	}


//	@Override
//	public ResponseEntity<Object> mobileOtpSender(RegistrationDto regMobileNumber) {
//		Set<Registration> findMobileInDb = registrationRepo.findByMobileNo(regMobileNumber.getMobileNo());
//		if (!findMobileInDb.isEmpty()) {
//			return ResponseHandler.responseBuilder(HttpStatus.ALREADY_REPORTED, "Already Registred");
//		} else {
//			String message;
//			String mobile =  regMobileNumber.getCountryCode() + regMobileNumber.getMobileNo();
//			this.otpgenerated = generateOTP();
//			try {
//				PhoneNumber to = new PhoneNumber(mobile);
//				PhoneNumber from = new PhoneNumber(twilioConfig.getTrailNumber());
//				message = this.otpgenerated + " This is Verification Code for Versatile ERP";
//				MessageCreator creator = Message.creator(to, from, message);
//				creator.create();
//			} catch (Exception e) {
//				 e.printStackTrace();
//				return ResponseHandler.responseBuilder(HttpStatus.BAD_REQUEST, "Try Again TWILIO ISSUE");
//			}
//			return ResponseHandler.responseBuilder(HttpStatus.CREATED, message);
//		}
//
//	}

//	@Override
//	public ResponseEntity<Object> voiceOtpSender(RegistrationDto mobileNo) {
//		Set<Registration> findMobileInDb = registrationRepo.findByMobileNo(mobileNo.getMobileNo());
//		if (!findMobileInDb.isEmpty()) {
//			return ResponseHandler.responseBuilder(HttpStatus.ALREADY_REPORTED, "Already Registred");
//		} else {
//			
//			String mobile =  mobileNo.getCountryCode() + mobileNo.getMobileNo();
//			this.otpgenerated = generateOTP();
//
//			// Create the TwiML response
//			Say say = new Say.Builder("Your OTP code for Versatile E R P is " + this.otpgenerated)
//					.voice(Say.Voice.WOMAN).language(Language.EN_IN).build();
//			VoiceResponse response = new VoiceResponse.Builder().say(say).build();
//
//			// Make the call using Twilio
//			 Call.creator(new com.twilio.type.PhoneNumber(mobile),
//					new com.twilio.type.PhoneNumber(twilioConfig.getTrailNumber()),
//					new com.twilio.type.Twiml(response.toXml())).create();
//			return ResponseHandler.responseBuilder(HttpStatus.CREATED, this.otpgenerated);
//		}
//	}
	/*
	 * 
	 * FOR REGISTRATION AND SAVING ADMIN IN USERS TABLE
	 * 
	 */
	@Override
	public ResponseEntity<Object> save(RegistrationDto regDto) {
		Registration findRegistrationinDb=registrationRepo.findByEmail(regDto.getEmail());
		if(findRegistrationinDb!=null) {
			return ResponseHandler.responseBuilder(HttpStatus.ALREADY_REPORTED, false);
		}
		else {
//		String adminRole = "Admin";
		Registration regModel = new Registration();
		BeanUtils.copyProperties(regDto, regModel);
		
//		Roles arole = roleRepo.findByRoleName(adminRole);
//		Set<Roles> roleSet = new HashSet<>();
//		Roles roleModel = arole;
//		if (roleModel != null) {
//			roleSet.add(roleModel);
//		}

		/*
		 * Getting Admin details from SignUp Form and Saving At UserDetails Document
		 */
//		Users udm = new Users();
//		udm.setEmail(regDto.getEmail());
//		udm.setRoleName(roleSet);		
//		String encryptedPasswordForUserDoc = passwordEncoder.encode(regDto.getPassword());
//		udm.setPassword(encryptedPasswordForUserDoc);
//		DateUtils.setBaseData(udm, "SYSTEM");
//		System.out.println("Udm"+udm.getCreatedAt());
//		userRepo.save(udm);
		/*
		 * 
		 * Saving Registration or SignUp details At Registration Document
		 * 
		 */
		String encryptPasswordForReg = passwordEncoder.encode(regModel.getPassword());
		regModel.setPassword(encryptPasswordForReg);
		DateUtils.setBaseData(regModel, "System");
		registrationRepo.save(regModel);

		return ResponseHandler.responseBuilder(HttpStatus.ACCEPTED, "Saved");
		}
	}
	
	@Override
	public ResponseEntity<Object> save(List<RegistrationDto> beans) {
		return null;
	}

	@Override
	public ResponseEntity<Object> update(RegistrationDto bean) {
		return null;
	}

	@Override
	public ResponseEntity<Object> delete(String id) {
		return null;
	}

	@Override
	public long getCount() {
		return 0;
	}

	@Override
	public long searchCount(SearchRequest request) {
		return 0;
	}
}