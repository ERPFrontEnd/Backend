package com.erp.blang.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.erp.blang.dto.RoleDTO;
import com.erp.blang.manager.RoleManager;
import com.erp.blang.model.Roles;
import com.erp.blang.repository.RoleRepository;
import com.erp.blang.responsehandle.ResponseHandler;

@Service
public class RoleManagerImpl implements RoleManager {

	@Autowired RoleRepository roleRepo;

//	@Override
//	public ResponseEntity<Object> save(RoleDTO roledto) {
//
//		Set<Roles> roleInDb = roleRepo.findByRoleName(roledto.getRoleName());
//
//		if (!roleInDb.isEmpty()) {
//			Roles role = new Roles();
//			BeanUtils.copyProperties(roledto, role);
//			roleRepo.save(role);
//			return new ResponseEntity<>("Saved", HttpStatus.ACCEPTED);
//		} else {
//			return new ResponseEntity<>("Role Already Exists", HttpStatus.NOT_ACCEPTABLE);
//		}
//	}

	@Override
	public ResponseEntity<Object> listAll() {
		List<Roles> roles = roleRepo.findAll();
		List<RoleDTO> roleDto = new ArrayList<>();
		for (Roles role : roles) {
			RoleDTO view = new RoleDTO();
			BeanUtils.copyProperties(role, view);
			if (!role.getRoleName().equals("Admin")) {
				roleDto.add(view);
			}
		}
		return ResponseHandler.responseBuilder(HttpStatus.ACCEPTED, roleDto);
	}
}
