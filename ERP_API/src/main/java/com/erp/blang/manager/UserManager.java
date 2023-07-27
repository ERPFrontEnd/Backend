package com.erp.blang.manager;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.erp.blang.core.AbstractService;
import com.erp.blang.dto.UsersDTO;
import com.erp.blang.filter.SearchRequest;
import com.erp.blang.model.Users;

public interface UserManager extends AbstractService<UsersDTO>{

	public ResponseEntity<Object> updatePassword(UsersDTO userDto);
	
	public ResponseEntity<Object> updateUserPreferences(UsersDTO userDto);

	List<Users> search(SearchRequest request);
}
