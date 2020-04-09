package com.spring.boot.rocks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.rocks.model.AppRole;
import com.spring.boot.rocks.repository.AppRoleRepository;

@Service
//@Transactional(timeout = 5)
public class AppRoleServiceImpl implements AppRoleService {

	@Autowired
	AppRoleRepository appRoleJPARepository;

	@Override
	public AppRole findByid(String rolename) {
		return appRoleJPARepository.findByname(rolename);
	}

}
