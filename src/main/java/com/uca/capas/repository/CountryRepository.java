package com.uca.capas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uca.capas.domain.Country;

public interface CountryRepository extends JpaRepository<Country, Integer>{

}
