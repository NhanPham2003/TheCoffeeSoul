package com.spring.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.entity.Shopping_Cart;
import com.spring.entity.Users;
import com.spring.repository.ShoppingCardRepository;
import com.spring.repository.UsersRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class AuthController {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	private ShoppingCardRepository SCRepo;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@GetMapping("/login")
	public String getLoginForm(Model model) {
		model.addAttribute("user", new Users());
		return "login";
	}

	@PostMapping("/login")
	public String checkLogin(Model model, 
			@Valid @ModelAttribute("user") Users user, 
			BindingResult result,
			HttpSession session,
			RedirectAttributes attributes) {
		
		String usernameOrEmail = request.getParameter("username");
		
		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		
		Boolean b = usernameOrEmail.matches(EMAIL_REGEX);
	
		if(result.hasErrors()) {
			return "login";
		}
		
		//login success
		
		if (b) {
			Users userFormDBEmail = usersRepository.findByEmailAndPassword(usernameOrEmail, user.getPassword());
			if (userFormDBEmail != null) {
				
				int role = userFormDBEmail.getRoleUser().getId();
				
				if (role == 1) {
					session.setAttribute("UserLoggerAdmin", userFormDBEmail);
					return "admin/home";
					
				}
				
				session.setAttribute("userLogged", userFormDBEmail);
				
				String fullName = userFormDBEmail.getFirstname() + " " + userFormDBEmail.getSurname();
				
				attributes.addFlashAttribute("messageFullName", fullName);
				
				model.addAttribute("messageLoginOrSignin", false);
				
				
				Shopping_Cart shoppingCart = SCRepo.findMaSCByIDUser(userFormDBEmail.getMaKhachHang());
				
				Date date = new Date();
				
				if (shoppingCart == null) {
					System.out.println("asas");
					Shopping_Cart shoppingCartNew = new Shopping_Cart(userFormDBEmail, date, true, null);
					SCRepo.save(shoppingCartNew);
					session.setAttribute("shoppingCartLogged", shoppingCartNew);
					return "redirect:/home";
					
				}else {
					session.setAttribute("shoppingCartLogged", shoppingCart);
					return "redirect:/home";
				}
				
			}
		}else {
			Users userFromDB = usersRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()); 
			if(userFromDB != null) {
				
				session.setAttribute("userLogged", userFromDB);
				
				String fullName = userFromDB.getFirstname() + " " + userFromDB.getSurname();
				
				attributes.addFlashAttribute("messageFullName", fullName);
				
				model.addAttribute("messageLoginOrSignin", false);
				
				Shopping_Cart shoppingCart = SCRepo.findMaSCByIDUser(userFromDB.getMaKhachHang());
				
				Date date = new Date();
				
				session.setAttribute("shoppingCartLogged", shoppingCart);
				if (shoppingCart == null) {
					Shopping_Cart shoppingCartNew = new Shopping_Cart(userFromDB, date, true, null);
					SCRepo.save(shoppingCartNew);
					
				}else {
					System.out.println("Shopping_Cart existed");
					return "redirect:/home";
				}
				
				
			}
		}
		
		
		
		int sumProductCart = (Integer) session.getAttribute("sumProductCart");
		model.addAttribute("sumProductCart", sumProductCart);
		
		//login failed
		model.addAttribute("message", "Username or Password incorrect");
		return "login";
		
	}
	
}
