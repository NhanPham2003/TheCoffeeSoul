package com.spring.controller;
 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
 
import com.spring.entity.Users; 

import jakarta.servlet.http.HttpSession;

@Controller
public class Blog_DetailesController {
	
	
	@GetMapping("/blog-details")
	public String blogDetailPage(Model model,
								 HttpSession session) {
		Users users =(Users)  session.getAttribute("userLogged");
		
		
		if (users !=null) {
			String fullname = users.getFirstname() + " " + users.getSurname();
			model.addAttribute("messageLoginOrSignIn", false);
			model.addAttribute("messageFullName", fullname);
		}
		
		int sumProductCart = (Integer) session.getAttribute("sumProductCart");
		model.addAttribute("sumProductCart", sumProductCart);
		
		model.addAttribute("messageLoginOrSignin", true);
	
		return "blog-details";
	}
}
