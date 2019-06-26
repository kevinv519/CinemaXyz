package com.uca.capas.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.Film;

public interface FilmService {
	
	List<Film> getAvailableMovies() throws DataAccessException;
}
