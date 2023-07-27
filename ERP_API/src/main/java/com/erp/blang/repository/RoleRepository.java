package com.erp.blang.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.erp.blang.model.Roles;

@Repository
public interface RoleRepository extends MongoRepository<Roles, String>{

	Roles findByRoleName(String adminRole);

}
