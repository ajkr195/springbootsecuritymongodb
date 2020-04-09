package com.spring.boot.rocks.service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.rocks.model.AppUser;
import com.spring.boot.rocks.repository.AppUserRepository;

@Service
//@Transactional(timeout = 5)
public class AppUserServiceImpl implements AppUserService {
	@Autowired
	private AppUserRepository appUserJPARepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(AppUser user) {
		user.setUsername(user.getUsername());
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(user.getRoles());
		
		user.setUseremail(user.getUseremail());
		user.setUserfirstname(user.getUserfirstname());
		user.setUserlastname(user.getUserlastname());
		user.setUseraddress(user.getUseraddress());
		System.out.println("\n%%%%%%%%%%%      Adding New User.... " + user.getUsername() + "     %%%%%%%%%%%%%\n");
		appUserJPARepository.save(user);
	}

	@Override
	public AppUser findByUsername(String username) {
		return appUserJPARepository.findByUsername(username);
	}


	@Override
	@XmlElement(name = "employee")
	public List<AppUser> findAllUsers() {
		List<AppUser> list = new ArrayList<>();
		appUserJPARepository.findAll().forEach(e -> list.add(e));
		return list;
	}
	
	@Override
	public void updateUser(AppUser user) {
		AppUser entity = appUserJPARepository.findByUsername(user.getUsername());
		if (entity != null) {
			System.out.println("\n%%%%%%%%%%%      Updating User.... " + user.getUsername() + "     %%%%%%%%%%%%%\n");

			entity.setUsername(user.getUsername());
			entity.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			entity.setUseremail(user.getUseremail());
			entity.setUserfirstname(user.getUserfirstname());
			entity.setUserlastname(user.getUserlastname());
			entity.setUseraddress(user.getUseraddress());
			entity.setRoles(user.getRoles());
			

		}
		appUserJPARepository.save(entity);
	}

	@Override
	public void deleteUserByUsername(String username) {
		System.out.println("\n%%%%%%%%%%%      Deleting User.... " + username + "     %%%%%%%%%%%%%\n");
		appUserJPARepository.delete(findByUsername(username));

	}
	

	

}
