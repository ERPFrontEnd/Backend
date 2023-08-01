package com.erp.blang.repository;

import java.util.Set;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.erp.blang.model.Registration;

@Repository
public interface RegistrationRepository extends MongoRepository<Registration, String> {

	Registration findByEmail(String mail);

//	Set<Registration> findByMobileNo(String mobileNo);

}
