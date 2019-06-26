package com.uca.capas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uca.capas.domain.Film;

public interface FilmRepository extends JpaRepository<Film, Integer>{

	List<Film> findByActiveOrderByTitleAsc(boolean active);
}
