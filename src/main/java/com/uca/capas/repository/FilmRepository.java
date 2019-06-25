package com.uca.capas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uca.capas.domain.Film;

public interface FilmRepository extends JpaRepository<Film, Integer>{

}
