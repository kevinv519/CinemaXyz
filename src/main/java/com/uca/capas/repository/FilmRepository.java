package com.uca.capas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uca.capas.domain.Film;

public interface FilmRepository extends JpaRepository<Film, Integer>{

	List<Film> findByActiveOrderByTitleAsc(boolean active);
	
	@Query("SELECT f FROM Film f LEFT JOIN FETCH f.showtimes WHERE f.id = ?1")
	Film fetchFilmWithShowtimes(Integer id);
}
