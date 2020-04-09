package com.spring.boot.rocks.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.spring.boot.rocks.model.AppUser;

public interface AppUserRepository extends MongoRepository<AppUser, String> {
	AppUser findByUsername(String username);
	
}
