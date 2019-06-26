package com.uca.capas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uca.capas.domain.User;
import com.uca.capas.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;
	
	@Override
	public User authenticateUser(String username, String password) throws DataAccessException {
		return userRepo.findFirstByUsernameAndPassword(username, password);
	}

	@Override
	@Transactional
	public void updateStatus(User u, Integer status) throws DataAccessException {
		u.setStatus(status);
		userRepo.saveAndFlush(u);
	}

}
