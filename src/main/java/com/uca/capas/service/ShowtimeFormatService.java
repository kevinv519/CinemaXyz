package com.uca.capas.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.ShowtimeFormat;



public interface ShowtimeFormatService {

	List<ShowtimeFormat> getShowtimeFormatList() throws DataAccessException;
	
	ShowtimeFormat getShowtimeFormat(Integer code) throws DataAccessException;
	
	void save(ShowtimeFormat showtimeFormat) throws DataAccessException;
}
