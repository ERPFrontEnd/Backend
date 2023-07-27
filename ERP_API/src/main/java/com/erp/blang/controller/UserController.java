package com.erp.blang.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.blang.dto.UsersDTO;
import com.erp.blang.manager.UserManager;
import com.erp.blang.utilities.APIEndpoints;

@RestController
@RequestMapping(path = APIEndpoints.BASE_API_URL_V1 + "/user")
//@PreAuthorize("hasRole('Admin')")
public class UserController {

	@Autowired
	UserManager userManager;

//	@ApiOperation(value="Adding CompanyDetails")
	@PostMapping("/save")
//	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<Object> addUser(@RequestBody UsersDTO userView) throws MessagingException {

		return userManager.save(userView);
	}

	@PutMapping("/update")
	public ResponseEntity<Object> updateUser(@RequestBody UsersDTO userDto) {
		return userManager.update(userDto);
	}
}
