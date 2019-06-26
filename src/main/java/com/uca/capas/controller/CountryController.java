package com.uca.capas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uca.capas.domain.Country;
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
	
	
}
