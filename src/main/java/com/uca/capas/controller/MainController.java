package com.uca.capas.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uca.capas.domain.User;
import com.uca.capas.service.UserService;

import com.uca.capas.service.FilmService;

@Controller
public class MainController {

	
	@Autowired
	UserService userService;
	
	Logger log = Logger.getLogger(MainController.class.getSimpleName());
	public final static String ATT_LOG = "logedIn";
	
	@Autowired
	private MessageSource messageSource;

	@Autowired
	FilmService filmService;

	/*@GetMapping("/")
	String home(Model model, ModelMap map, HttpSession session) {
		if (session.getAttribute(ATT_LOG) != null && (boolean)session.getAttribute(ATT_LOG)) {
			return "redirect:/stores";
		}
		model.mergeAttributes(map);
		return "home";
	}*/
		
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
	
}
