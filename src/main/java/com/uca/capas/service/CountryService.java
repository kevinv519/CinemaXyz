package com.uca.capas.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.Country;

public interface CountryService {

	List<Country> getCountryList() throws DataAccessException;
	
	Country getCountry(Integer code) throws DataAccessException;
	
	void save(Country country) throws DataAccessException;
}
