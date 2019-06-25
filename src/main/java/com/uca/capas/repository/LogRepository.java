package com.uca.capas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uca.capas.domain.LogAction;

public interface LogRepository extends JpaRepository<LogAction, Integer> {

}
