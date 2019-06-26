package com.uca.capas.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.Auditorium;

public interface AuditoriumService {

	List<Auditorium> getAuditoriumList() throws DataAccessException;
	
	Auditorium getAuditorium(Integer code) throws DataAccessException;
	
	void save(Auditorium auditorium) throws DataAccessException;
}
