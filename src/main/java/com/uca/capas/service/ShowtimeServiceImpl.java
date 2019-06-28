package com.uca.capas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.uca.capas.domain.Showtime;
import com.uca.capas.repository.ShowtimeRepository;

@Service
public class ShowtimeServiceImpl implements ShowtimeService {

	@Autowired
	ShowtimeRepository stRepo;
	@Override
	public Showtime showtimeFilm(Integer film_id) throws DataAccessException {
		return stRepo.getOneByfilm_id(film_id);
	}

}
