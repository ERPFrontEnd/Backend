package com.erp.blang.manager.impl;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.erp.blang.core.GenericSearchRepository;
import com.erp.blang.dto.UsersDTO;
import com.erp.blang.filter.SearchRequest;
import com.erp.blang.manager.UserManager;
import com.erp.blang.model.Roles;
import com.erp.blang.model.Users;
import com.erp.blang.repository.RoleRepository;
import com.erp.blang.repository.UserRepository;
import com.erp.blang.responsehandle.ResponseHandler;

@Service
public class UserManagerImpl implements UserManager {

	private static final String CHARACTERS = "@#!&*?/0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final int TEMP_PASSWORD_LENGTH = 8;
	String userTempPassword;

	private Random random = new SecureRandom();
	
	@Autowired GenericSearchRepository genericSearchRepository;
	@Autowired JavaMailSender mailSender1;
	@Autowired UserRepository userRepo;
	@Autowired RoleRepository roleRepo;
	@Autowired BCryptPasswordEncoder encoder;

	@Override
	public ResponseEntity<Object> save(UsersDTO view) {
		Users optional = userRepo.findByEmail(view.getEmail());
		Users info = new Users();
		
		if (optional!=null) {
			return ResponseHandler.responseBuilder(HttpStatus.ALREADY_REPORTED, "Already Exist");
		} else {
			
			BeanUtils.copyProperties(view, info);
			Set<String> strRoles = view.getRoleName();
			Set<Roles> rl=new HashSet<>();
				for(String role:strRoles) {
				Roles findroles=roleRepo.findByRoleName(role);
				if(findroles!=null ) {
					rl.add(findroles);
				}
			}
				info.setRoleName(rl);
			// Sending Temporary Password to Users Mail, before Encryption when Admin
			// Register User for First Time
			try {
				this.mailSender(info.getEmail());
			} catch (MessagingException e) {
				return ResponseHandler.responseBuilder(HttpStatus.BAD_REQUEST, "Try Again");
			}
			// Users Temporary Password Encryption
			String encryptedPasswordForUserDoc = encoder.encode(userTempPassword);
			info.setPassword(encryptedPasswordForUserDoc);

			userRepo.save(info);
			return ResponseHandler.responseBuilder(HttpStatus.CREATED, "User details saved");
		}
	}
	/*
	 * Generating Temporary password when Admin Register's User for First Time
	 */
	public String generateTempUsersPassword() {
		StringBuilder otp = new StringBuilder(TEMP_PASSWORD_LENGTH);
		for (int i = 0; i < TEMP_PASSWORD_LENGTH; i++) {
			int randomIndex = random.nextInt(CHARACTERS.length());
			otp.append(CHARACTERS.charAt(randomIndex));
		}
		return otp.toString();
	}

	/*
	 * Sending Password to when Admin Register's User for First Time
	 */
	public void mailSender(String adminAddedUser) throws MessagingException {
		MimeMessage mimeMessage = mailSender1.createMimeMessage();
		MimeMessageHelper mimeMessageHelper;
		this.userTempPassword = generateTempUsersPassword();
		mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		mimeMessageHelper.setTo(adminAddedUser);
		mimeMessageHelper.setFrom("hariofficial137@gmail.com");
		mimeMessageHelper.setSubject("Temporary Password for Versatile ERP");
		mimeMessageHelper.setText("This is Temporary Password :   " + userTempPassword, true);
		mailSender1.send(mimeMessage);
	}

	@Override
	public ResponseEntity<Object> update(UsersDTO userDto) {
		Optional<Users> findUser = userRepo.findById(userDto.getId());
		if (findUser.isPresent()) {
			Users user = new Users();
			BeanUtils.copyProperties(userDto, user);
			if(userDto.getEmail() != null) {
				user.setEmail(userDto.getEmail());
			}else {
				Users us=findUser.get();
				user.setEmail(us.getEmail());
			}
			if(userDto.getMobileNumber() != null) {
				user.setMobileNumber(userDto.getMobileNumber());
			}else {
				Users us=findUser.get();
				user.setMobileNumber(us.getMobileNumber());
			}
			if(userDto.getUserFirstName() != null) {
				user.setUserFirstName(userDto.getUserFirstName());
			}else {
				Users us=findUser.get();
				user.setUserFirstName(us.getUserFirstName());
			}
			if(userDto.getUserLastName() != null) {
				user.setUserLastName(userDto.getUserLastName());
			}else {
				Users us=findUser.get();
				user.setUserLastName(us.getUserLastName());
			}
			if(userDto.getUserdisplayName() != null) {
				user.setUserdisplayName(userDto.getUserdisplayName());
			}else {
				Users us=findUser.get();
				user.setUserdisplayName(us.getUserdisplayName());
			}
			if(userDto.getRoleName() != null) {
			Set<String> strRoles = userDto.getRoleName();
			Set<Roles> rl=new HashSet<>();
				for(String role:strRoles) {
				Roles findroles=roleRepo.findByRoleName(role);
				if(findroles!=null ) {
					rl.add(findroles);
				}
			}
				user.setRoleName(rl);
			}
			else {
				Users us=findUser.get();
				user.setRoleName(us.getRoleName());
			}
			if(userDto.getPassword() != null) {
			String encryptedPasswordForUserDoc = encoder.encode(user.getPassword());
			user.setPassword(encryptedPasswordForUserDoc);
			}
			else {
				Users us=findUser.get();
				user.setPassword(us.getPassword());
			}
			if(userDto.getUserPreferences() != null) {
				user.setUserPreferences(userDto.getUserPreferences());
				user.setInitialLoginSetUp(true);
			}
			else {
				user.setInitialLoginSetUp(false);
				Users us=findUser.get();
				user.setUserPreferences(us.getUserPreferences());
			}
			userRepo.save(user);
			return ResponseHandler.responseBuilder(HttpStatus.ACCEPTED, "Updated User");
		} else {
			return ResponseHandler.responseBuilder(HttpStatus.NOT_FOUND, "User Not Found To Update");
		}
	}

	@Override
	public ResponseEntity<Object> save(List<UsersDTO> beans) {
		return null;
	}

	@Override
	public ResponseEntity<Object> delete(String id) {
		return null;
	}

	@Override
	public ResponseEntity<Object> updateUserPreferences(UsersDTO userDto) {
		return null;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Users> search(SearchRequest request){
		return (List<Users>) genericSearchRepository.search(request, Users.class);
		
	}
	@Override
	public long getCount() {
		return userRepo.count();
	}
	@Override
	public long searchCount(SearchRequest request) {
		return genericSearchRepository.searchCount(request, Users.class);
	}
	@Override
	public ResponseEntity<Object> updatePassword(UsersDTO userDto) {
		return null;
	}

}
