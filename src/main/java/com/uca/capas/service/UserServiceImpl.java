package com.uca.capas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uca.capas.domain.User;
import com.uca.capas.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository uRepo;
	
	@Override
	public User verify(String username, String password) {
		return  uRepo.findFirstByUsernameAndPassword(username, password);
	}

}
