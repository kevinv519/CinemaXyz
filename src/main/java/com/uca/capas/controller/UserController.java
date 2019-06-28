package com.uca.capas.controller;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uca.capas.domain.User;
import com.uca.capas.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	Logger logger = Logger.getLogger(UserController.class.getSimpleName());
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
	UserService uService;

	@PostMapping("/validate")
	String login(@RequestParam String usern, @RequestParam String pass, RedirectAttributes ra, HttpSession session, 
			@SessionAttribute(name = User.SESSION_ATT_LOG, required = false) User loggedUser, Model model) {
		if (loggedUser != null) {
			ra.addFlashAttribute("error", messageSource.getMessage("login.error.logged-in-browser", new Object[]{}, Locale.getDefault()));
			return "redirect:/login";
		}
		if (usern.trim().isEmpty() || pass.trim().isEmpty()) {
			ra.addFlashAttribute("error", "Todos los campos son obligatorios");
		} else {
			try {
				User user = uService.authenticateUser(usern, pass);
				if (user != null) {
					if (user.getStatus() == User.DISABLED) {
						ra.addFlashAttribute("error", messageSource.getMessage("login.error.disabled", new Object[]{}, Locale.getDefault()));
					} else if (user.getStatus() == User.PENDING) {
						ra.addFlashAttribute("error", messageSource.getMessage("login.error.pending", new Object[]{}, Locale.getDefault()));
					} else if (user.getStatus() == User.LOGGED_IN) {
						ra.addFlashAttribute("error", messageSource.getMessage("login.error.logged-in", new Object[]{}, Locale.getDefault()));
					} else {
						session.setAttribute(User.SESSION_ATT_LOG, user);
						if(user.getIsAdmin()) {
						return "redirect:/admin/movies";
						}else {
							return "redirect:/";
						}
					}
					return "redirect:/login";
				}
				ra.addFlashAttribute("error", "Usuario o contraseña incorrecto");
			} catch(Exception e) {
				logger.log(Level.SEVERE, "Could not retrieve user", e);
				ra.addFlashAttribute("error", messageSource.getMessage("login.error.exception", new Object[]{}, Locale.getDefault()));
			}
		}
		return "redirect:/login";
	}
	
}
