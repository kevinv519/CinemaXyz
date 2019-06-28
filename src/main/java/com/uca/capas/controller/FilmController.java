package com.uca.capas.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	ModelAndView listFilms(@SessionAttribute(name = User.SESSION_ATT_LOG, required = false) User u,
			RedirectAttributes ra, ModelMap map) {
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
				mav.getModelMap().mergeAttributes(map);
				mav.addObject("films", films);
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Could not retrieve films", e);
		}
		mav.setViewName("admin/movies/list");
		return mav;
	}

	@GetMapping("/add")
	String addFilm(@SessionAttribute(name = User.SESSION_ATT_LOG, required = false) User u, RedirectAttributes ra,
			Model model) {
		if (u == null) {
			ra.addFlashAttribute("error", "Debes iniciar sesión para acceder a esta acción");
			return "redirect:/login";
		}
		model.addAttribute("action", "Agregar");
		model.addAttribute("film", new Film());
		return "admin/movies/form";
	}

	@GetMapping("/edit/{id}")
	ModelAndView editFilm(@SessionAttribute(name = User.SESSION_ATT_LOG, required = false) User u, @PathVariable Integer id,
			RedirectAttributes ra) {
		ModelAndView mav = new ModelAndView();
		if (u == null) {
			ra.addFlashAttribute("error", "Debes iniciar sesión para acceder a esta acción");
			mav.setViewName("redirect:/login");
		}
		try {
			Film film = filmService.getMovie(id);
			if (film != null) {
				mav.addObject("film", film);
				mav.addObject("action", "Editar");
				mav.setViewName("admin/movies/form");
				return mav;
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "No se pudo obtener la película. Detalles: ", e);
		}
		ra.addFlashAttribute("success", false);
		ra.addFlashAttribute("message", "Ocurrió un error y no se pudo cargar la información de la película");
		mav.setViewName("redirect:/admin/movies");
		return mav;
	}

	@PostMapping("/save")
	String save(@SessionAttribute(name = User.SESSION_ATT_LOG, required = false) User u, Model model,
			@Valid @ModelAttribute Film film, BindingResult br, RedirectAttributes ra) {
		if (u == null) {
			ra.addFlashAttribute("error", "Debes iniciar sesión para acceder a esta acción");
			return "redirect:/login";
		}
		if (br.hasErrors()) {
			model.addAttribute("action", film.getId() == null ? "Agregar" : "Editar");
			return "admin/movies/form";
		}
		try {
			filmService.save(film, u.getUsername());
			ra.addFlashAttribute("success", true);
			ra.addFlashAttribute("message", "Película guardada con éxito");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "No se pudo guardar la película. Detalles: ", e);
			ra.addFlashAttribute("success", false);
			ra.addFlashAttribute("message", "Ocurrió un error y no se pudo guardar la información");
		}
		return "redirect:/admin/movies";
	}
	
	@GetMapping("/activate/{id}")
	String activate(@SessionAttribute(name = User.SESSION_ATT_LOG, required = false) User u, RedirectAttributes ra,
			@PathVariable Integer id) {
		if (u == null) {
			ra.addFlashAttribute("error", "Debes iniciar sesión para acceder a esta acción");
			return "redirect:/login";
		}
		try {
			Film film = filmService.getMovie(id);
			if (film != null) {
				film.setActive(!film.getActive());
				filmService.save(film, u.getUsername());
				ra.addFlashAttribute("success", true);
				ra.addFlashAttribute("message", "Estado actualizado con éxito");
				return "redirect:/admin/movies";
			} else {
				ra.addFlashAttribute("success", false);
				ra.addFlashAttribute("message", "Ocurrió un error y no se pudo guardar la información");
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "No se pudo guardar la película. Detalles: ", e);
			ra.addFlashAttribute("success", false);
			ra.addFlashAttribute("message", "Ocurrió un error y no se pudo guardar la información");
		}
		return "redirect:/admin/movies";
	}
	
}
