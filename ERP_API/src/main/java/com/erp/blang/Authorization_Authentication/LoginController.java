package com.erp.blang.Authorization_Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.blang.controller.GenericController;
import com.erp.blang.utilities.APIEndpoints;



@RestController
@CrossOrigin
@RequestMapping(path = APIEndpoints.BASE_API_URL_V1 + "/signin")
public class LoginController extends GenericController{

	@Autowired UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<Object> createJwtToken(@RequestBody LoginRequest loginRequest) throws Exception {
		return userService.createJwtToken(loginRequest);
	}
}
