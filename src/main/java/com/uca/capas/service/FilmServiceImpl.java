package com.uca.capas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
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

	@Override
	public List<Film> getAllMovies() throws DataAccessException {
		return filmRepo.findAll(Sort.by("active", "title"));
	}

	@Override
	public Film getMovie(Integer id) throws DataAccessException {
		return filmRepo.getOne(id);
	}

	@Override
	public Film getMovieWithShowtime(Integer id) throws DataAccessException {
		return filmRepo.fetchFilmWithShowtimes(id);
	}

}
