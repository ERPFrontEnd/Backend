package com.erp.blang.Authorization_Authentication;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.erp.blang.dto.RoleDTO;
import com.erp.blang.model.Roles;
import com.erp.blang.model.Users;
import com.erp.blang.repository.RoleRepository;
import com.erp.blang.repository.UserRepository;
import com.erp.blang.responsehandle.ResponseHandler;
		

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private RoleRepository roleRepo;	
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private JWTUtility utility;

//	 @Autowired
//	 private AuthenticationManager authenticationManager;

	public ResponseEntity<Object> createJwtToken(LoginRequest loginReq) {
		String email = loginReq.getUserName();
		String password = loginReq.getPassword();
		final UserDetails userDetails = loadUserByUsername(email);
//		boolean login=authenticate(email, password);
		String newGeneratedToken = utility.generateToken(userDetails);
		LoginResponse lr = new LoginResponse();

		
		Users users = userRepo.findByEmail(email);
		if(users != null && userDetails != null && password != null && email != null) {
			
		BeanUtils.copyProperties(users, lr);
		
		lr.setJwtToken(newGeneratedToken);
		Roles rolesModel = new Roles();
		RoleDTO rolesDto = new RoleDTO();
		BeanUtils.copyProperties(rolesModel, rolesDto);
		return ResponseHandler.responseBuilder(HttpStatus.ACCEPTED,lr);
		}
		else {
			return ResponseHandler.responseBuilder(HttpStatus.NOT_ACCEPTABLE,"Wrong Credits");
		}
	}

	private Set<GrantedAuthority> getAuthorities(Users userModel) {
	    Set<GrantedAuthority> authorities = new HashSet<>();
	    userModel.getRoleName().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName())));
	    return authorities;
	}

	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	    Users userOptional = userRepo.findByEmail(email);
	    if (userOptional != null) {
	    	Set<GrantedAuthority> authorities = getAuthorities(userOptional);
		    return new User(userOptional.getEmail(), userOptional.getPassword(), authorities);
	        // Return a UserDetails object with "No user" as the username and empty password and authorities  
	    }
	    return new User("No user", "", Collections.emptyList());

	    }


/*
 * done
 */
//	private boolean authenticate(String email, String password) 	 {
//		try {
//		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
//			return true;
//		} catch (DisabledException | BadCredentialsException e) {
//			return false;
//		} 
//	}
}
