package com.uca.capas.service;

import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.User;

public interface UserService {
	
	User authenticateUser(String username, String password) throws DataAccessException;
	
	void updateStatus(User u, Integer status) throws DataAccessException;
}
