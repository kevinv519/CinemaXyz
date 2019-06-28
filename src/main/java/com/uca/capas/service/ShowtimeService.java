package com.uca.capas.service;

import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.Showtime;

public interface ShowtimeService {
	public Showtime showtimeFilm(Integer film_id) throws DataAccessException;
}
