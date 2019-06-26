package com.uca.capas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uca.capas.domain.Auditorium;
import com.uca.capas.service.AuditoriumService;

@Controller
@RequestMapping("/auditoria")
public class AuditoriumController {
	@Autowired
	AuditoriumService auditoriumService;
	
	@GetMapping("/add")
	String addCountry(Model model) {
		model.addAttribute("auditorium", new Auditorium());
		return "auditorium_form";
	}
}
