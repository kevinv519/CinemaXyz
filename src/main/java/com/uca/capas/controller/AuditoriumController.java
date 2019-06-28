package com.uca.capas.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.uca.capas.domain.Auditorium;
import com.uca.capas.domain.User;
import com.uca.capas.service.AuditoriumService;

@Controller
@RequestMapping("/admin/auditoria")
public class AuditoriumController {
	Logger logger = Logger.getLogger(FilmController.class.getSimpleName());
	
	@Autowired
	AuditoriumService auditoriumService;
	
	@GetMapping()
	ModelAndView listAuditoria(@SessionAttribute(name = User.SESSION_ATT_LOG, required = false) User u, RedirectAttributes ra) {
		ModelAndView mav = new ModelAndView();
		if (u == null) {
			ra.addFlashAttribute("error", "Debes iniciar sesión para acceder a esta vista");
			mav.setViewName("redirect:/login");
			return mav;
		}
		List<Auditorium> auditoria;
		try {
			auditoria = auditoriumService.getAuditoriumList();
			if (auditoria != null) {
				mav.addObject("auditoria", auditoria);
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "No se pudo cargar la lista de salas", e);
		}
		mav.setViewName("admin/auditoria/list");
		return mav;
	}
	

	@GetMapping("/add")
	String addAuditorium(Model model) {
		model.addAttribute("auditorium", new Auditorium());
		return "auditorium_form";
	}
	
	@RequestMapping(path="/save", method = RequestMethod.POST)
	public ModelAndView saveAuditorium(@Valid @ModelAttribute("auditorium") Auditorium auditorium, BindingResult result, RedirectAttributes ra,
			@SessionAttribute(name = User.SESSION_ATT_LOG, required = false) User u){
		ModelAndView mav = new	 ModelAndView();
		if (u == null) {
			ra.addFlashAttribute("error", "Debes iniciar sesión para acceder a esta vista");
			mav.setViewName("redirect:/login");
			return mav;
		}
		if(result.hasErrors()) {
			mav.addObject("action", auditorium.getId() == null ? "Agregar":"Editar");
			mav.setViewName("auditorium_form");
		} else {
			try {
				System.out.println(auditorium.getId());
				auditoriumService.save(auditorium, u.getUsername());
				RedirectView rv = new RedirectView("/admin/auditoria");
				rv.setExposeModelAttributes(false);
				ra.addFlashAttribute("message", "Cambios realizados con éxito.");	
				mav.setView(rv);
			}
			catch(Exception e) {
				mav.addObject("action", auditorium.getId() == null ? "Agregar":"Editar");
				mav.addObject("message", "Oops. No se pudieron realizar los cambios.");
				mav.setViewName("auditorium_form");
				e.printStackTrace();
			}
		}
		return mav;
	}

	@RequestMapping("/countries/edit/{id}")
	public String editStore(@PathVariable("id") Integer code, Model m){
		try {
			Auditorium auditorium = auditoriumService.getAuditorium(code);
			m.addAttribute("action", "Editar");
			m.addAttribute("auditorium", auditorium);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return "auditorium_form";
}

}
