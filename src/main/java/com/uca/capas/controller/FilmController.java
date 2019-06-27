package com.uca.capas.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uca.capas.domain.Film;
import com.uca.capas.domain.User;
import com.uca.capas.service.FilmService;

@Controller
@RequestMapping("/admin/movies")
public class FilmController {
	Logger logger = Logger.getLogger(FilmController.class.getSimpleName());
	
	@Autowired
	FilmService filmService;
	
	@GetMapping()
	ModelAndView listFilms(@SessionAttribute(name = User.SESSION_ATT_LOG, required = false) User u, RedirectAttributes ra) {
		ModelAndView mav = new ModelAndView();
		if (u == null) {
			ra.addFlashAttribute("error", "Debes iniciar sesión para acceder a esta vista");
			mav.setViewName("redirect:/login");
			return mav;
		}
		List<Film> films;
		try {
			films = filmService.getAllMovies();
			if (films != null) {
				mav.addObject("films", films);
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Could not retrieve films", e);
		}
		mav.setViewName("admin/movies/list");
		return mav;
	}
}
