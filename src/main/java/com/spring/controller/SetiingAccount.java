package com.spring.controller;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.entity.Users;
import com.spring.repository.UsersRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class SetiingAccount {

	@Autowired
	private HttpSession session;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	private UsersRepository usersRepository;
	
	@GetMapping("/settingAccount")
	public String SettingUserAccount(Model model) {
		Users users =(Users)  session.getAttribute("userLogged");
		if (users!=null) {
			
			int sumProductCart = (Integer) session.getAttribute("sumProductCart");
			model.addAttribute("sumProductCart", sumProductCart);
			
			String fullname = users.getFirstname() + " " + users.getSurname();
			model.addAttribute("messageLoginOrSignin", false);
			model.addAttribute("messageFullName", fullname);
		}
		
		model.addAttribute("users", users);
		
		return "settingAccount";
	}
	
	@PostMapping("/updateUser")
	public String updateAccount(Model model) {
		
		Users users =(Users)  session.getAttribute("userLogged");
		
		int idUser = users.getMaKhachHang();
		
		String username = request.getParameter("username");
		String firstname = request.getParameter("firstname");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String dateOfBirth = request.getParameter("dateOfBirth");
		
		String usernameCheck = users.getUsername();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Users userCheck = usersRepository.findByUsername(username);
		
		if (userCheck != null) {
			model.addAttribute("messageUsername", "username invalid");
			model.addAttribute("users", users);
			return"settingAccount";
		}else{
			try {
				System.out.println( phone);
	            Date date = dateFormat.parse(dateOfBirth);
    			users.setFirstname(firstname);
    			users.setSurname(surname);
    			users.setEmail(email);
    			users.setPhone(phone);
    			users.setDateOfBirth(date);
    			
    			usersRepository.save(users);
    			
	        } catch (ParseException e) {
	            e.printStackTrace();
	        } 
		}
		
		Users userNew = usersRepository.findById(idUser).orElse(userCheck);
		
		session.removeAttribute("userLogged");
		
		session.setAttribute("userLogged", userNew);
		
		model.addAttribute("users", userNew);
		
		return"settingAccount";
		
	}
}
