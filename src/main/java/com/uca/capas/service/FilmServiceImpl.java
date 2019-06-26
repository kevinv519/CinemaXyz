package com.uca.capas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.uca.capas.domain.Film;
import com.uca.capas.repository.FilmRepository;

@Service
public class FilmServiceImpl implements FilmService {

	@Autowired
	FilmRepository filmRepo;
	
	@Override
	public List<Film> getAvailableMovies() throws DataAccessException {
		return filmRepo.findByActiveOrderByTitleAsc(true);
	}

}
