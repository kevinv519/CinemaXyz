package com.uca.capas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.uca.capas.domain.ShowtimeFormat;

import com.uca.capas.repository.ShowtimeFormatRepo;

@Service
public class ShowtimeFormatServiceImpl implements ShowtimeFormatService{

	@Autowired
	ShowtimeFormatRepo stRepository;

	@Override
	public List<ShowtimeFormat> getShowtimeFormatList() throws DataAccessException {
		return stRepository.findAll(Sort.by("name"));
	}

	@Override
	public ShowtimeFormat getShowtimeFormat(Integer code) throws DataAccessException {
		Optional<ShowtimeFormat> showtimeFormat = stRepository.findById(code);
		return showtimeFormat.isPresent()? showtimeFormat.get() : null;
	}

	@Override
	public void save(ShowtimeFormat showtimeFormat) throws DataAccessException {
		stRepository.save(showtimeFormat);
	}
	
		
}
