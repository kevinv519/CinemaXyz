package com.uca.capas.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.uca.capas.domain.Country;
import com.uca.capas.repository.CountryRepository;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	CountryRepository countryRepo;
	
	@Override
	public List<Country> getCountryList() throws DataAccessException {
		return countryRepo.findAll(Sort.by("iso"));
	}

	@Override
	public Country getCountry(Integer code) throws DataAccessException {
		Optional<Country> country = countryRepo.findById(code);
		return country.isPresent()? country.get() : null;
	}

	@Override
	@Transactional
	public void save(Country country) throws DataAccessException {
		countryRepo.save(country);
	}

}
