package com.uca.capas.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.Film;

public interface FilmService {
	
	List<Film> getAvailableMovies() throws DataAccessException;
	
	List<Film> getAllMovies() throws DataAccessException;
	
	void save(Film film, String username) throws DataAccessException;
	
	Film getMovie(Integer id) throws DataAccessException;

	Film getMovieWithShowtime(Integer id) throws DataAccessException;
}
