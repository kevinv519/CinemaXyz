package com.uca.capas.controller;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.uca.capas.service.FilmService;

@Controller
public class MainController {
	Logger log = Logger.getLogger(MainController.class.getSimpleName());

	@Autowired
	private MessageSource messageSource;

	@Autowired
	FilmService filmService;

	@GetMapping("/")
	String home(Model model) {
		try {
			model.addAttribute("films", filmService.getAvailableMovies());
		} catch (Exception e) {
			log.log(Level.SEVERE, "Could not retrieve films. Details: ", e);
			model.addAttribute("hasMessage", true);
			model.addAttribute("isError", true);
			model.addAttribute("message",
					messageSource.getMessage("home.errorLoading", new Object[] {}, Locale.getDefault()));
		}
		return "home";
	}
	
	@GetMapping("/login")
	String loginForm() {
		return "login";
	}
}
