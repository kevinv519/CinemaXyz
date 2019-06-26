package com.uca.capas.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
public class MainController {
	
	@Autowired
	UserService userService;
	
	Logger log = Logger.getLogger(MainController.class.getSimpleName());
	public final static String ATT_LOG = "logedIn";
	
	@GetMapping("/")
	String home(Model model, ModelMap map, HttpSession session) {
		if (session.getAttribute(ATT_LOG) != null && (boolean)session.getAttribute(ATT_LOG)) {
			return "redirect:/stores";
		}
		model.mergeAttributes(map);
		
		return "home";
	}
	
	
	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password, RedirectAttributes ra, HttpSession session) {
		if (username.trim().isEmpty() || password.trim().isEmpty()) {
			ra.addFlashAttribute("error", "Todos los campos son obligatorios");
		}
		else {
			try {
				User user = userService.verify(username, password);
				if (user != null) {
					session.setAttribute(ATT_LOG, true);
					return "redirect:/control";
				}
				ra.addFlashAttribute("error", "Usuario o contraseña inválidos");
			}catch (Exception e) {
				log.log(Level.SEVERE, "Could not retrieve user", e);
				ra.addFlashAttribute("error", "No se pudo autenticar el usuario. Intenté más tarde o contacte el administrador del sitio");
			}
		}
		return "redirect:/";
	}
		
	@GetMapping("/logout")
	String logout(HttpSession session) {
		session.setAttribute(ATT_LOG, false);
		return "redirect:/";
	}
	
	@GetMapping("/control")
	String control(){
		return "control";		
	}
}
