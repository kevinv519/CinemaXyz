package com.uca.capas.controller;

import javax.servlet.http.HttpServletRequest;
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

import com.uca.capas.domain.Country;
import com.uca.capas.domain.User;
import com.uca.capas.service.CountryService;

@Controller
@RequestMapping("/countries")
public class CountryController {

	@Autowired
	CountryService countryService;
	
	@GetMapping("/add")
	String addCountry(Model model) {
		model.addAttribute("country", new Country());
		return "country_form";
	}
	
	@RequestMapping(path="/save", method = RequestMethod.POST)
	public ModelAndView saveStore(@Valid @ModelAttribute("country") Country country, BindingResult result, RedirectAttributes ra,HttpServletRequest req,
			@SessionAttribute(name = User.SESSION_ATT_LOG, required = false) User u){
		ModelAndView mav = new	 ModelAndView();
		if (u == null) {
			ra.addFlashAttribute("error", "Debes iniciar sesión para acceder a esta vista");
			mav.setViewName("redirect:/login");
			return mav;
		}
		if(result.hasErrors()) {
			mav.addObject("action", country.getId() == null ? "Agregar":"Editar");
			mav.setViewName("country_form");
		} else {
			try {
				System.out.println(country.getId());
				countryService.save(country, u.getUsername());
				RedirectView rv = new RedirectView(req.getContextPath()+"/");
				rv.setExposeModelAttributes(false);
				ra.addFlashAttribute("message", "Cambios realizados con éxito.");	
				mav.setView(rv);
			}
			catch(Exception e) {
				mav.addObject("action", country.getId() == null ? "Agregar":"Editar");
				mav.addObject("message", "Oops. No se pudieron realizar los cambios.");
				mav.setViewName("country_form");
				e.printStackTrace();
			}
		}
		return mav;
	}

	@RequestMapping("/countries/edit/{id}")
	public String editStore(@PathVariable("id") Integer code, Model m){
		try {
			Country country = countryService.getCountry(code);
			m.addAttribute("action", "Editar");
			m.addAttribute("country", country);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return "country_form";
}
	
}
