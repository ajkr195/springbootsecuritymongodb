package com.spring.boot.rocks.service;

import java.util.List;
import java.util.Map;

import com.spring.boot.rocks.model.AppUser;

public interface AppUserService {
	AppUser findByUsername(String username);

	void save(AppUser user);

	List<AppUser> findAllUsers();
	
	void updateUser(AppUser user);

	void deleteUserByUsername(String emailid);
	
}
