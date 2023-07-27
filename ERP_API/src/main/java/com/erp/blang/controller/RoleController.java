package com.erp.blang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erp.blang.manager.RoleManager;
import com.erp.blang.utilities.APIEndpoints;

@RestController
@RequestMapping(path = APIEndpoints.BASE_API_URL_V1 + "/roles")
//@PreAuthorize("hasRole('Admin')")
public class RoleController {

	@Autowired
	RoleManager roleManager;

	//	@PostMapping("/SaveRole")
//	public ResponseEntity<Object> saveRole(@RequestBody RoleDTO roleDto) {
//		return new ResponseEntity<>(roleManager.save(roleDto), HttpStatus.ACCEPTED);
//	}

	@GetMapping("/getAll")
	public ResponseEntity<Object> getAllRoles() {
		return roleManager.listAll();
	}

}
