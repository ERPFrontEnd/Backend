package com.erp.blang.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.erp.blang.model.Users;

@Repository
public interface UserRepository extends MongoRepository<Users, String> {

	Users findByEmail(String email);
	Users findByMobileNumber(String mobile);
}
