package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class Error404NotFoundController {

	@GetMapping("/404")
	public String error404NotFound(Model model) {
		return"error/404";
	}
}
