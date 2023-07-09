package com.spring.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
 
import com.spring.entity.Users; 

import jakarta.servlet.http.HttpSession;

@Controller
public class ContactController {
 
	
	@GetMapping("/contact")
	public String contactPage(Model model, 
							  HttpSession session) {
		Users users = (Users) session.getAttribute("userLogged");
		
		if (users != null) {
			
			int sumProductCart = (Integer) session.getAttribute("sumProductCart");
			model.addAttribute("sumProductCart", sumProductCart);
			
			String fullname = users.getFirstname() + " " + users.getSurname();
			model.addAttribute("messageLoginOrSignin", false);
			model.addAttribute("messageFullName",fullname );
		}else {
			model.addAttribute("messageLoginOrSignin", true);
		}
		
		
		return "contact";
	}
}
