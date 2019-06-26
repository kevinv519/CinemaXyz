package com.uca.capas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uca.capas.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findFirstByUsernameAndPassword(String username, String password);
}
