package com.uca.capas.service;

import com.uca.capas.domain.User;

public interface UserService {
	public User verify(String username, String password);
}
