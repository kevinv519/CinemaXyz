package com.uca.capas.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		return filmRepo.findAll(Sort.by(Order.desc("active"), Order.asc("title")));
	}

	@Override
	@Transactional
	public void save(Film film, String username) throws DataAccessException {
		Date date = Calendar.getInstance().getTime();
		if (film.getId() == null || film.getCreatedDate() == null) {
			film.setCreatedDate(date);
			film.setCreatedBy(username);
		}
		film.setRating(film.getRating().toUpperCase());
		film.setUpdatedBy(username);
		film.setUpdatedDate(date);
		filmRepo.save(film);
	}

}
