package com.spring.boot.rocks.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.spring.boot.rocks.model.AppRole;

public interface AppRoleRepository extends MongoRepository<AppRole, String> {
	AppRole findByname(String rolename);
}
