package com.spring.controller;

import java.util.Random;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.entity.Users;
import com.spring.repository.UsersRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/forgotPass")
public class ForgotPassController {

	
	private Random random = new Random();

	@Autowired
	HttpServletRequest request;

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private SendMailServices sendMailService;
	
	
	@GetMapping("/forgotPassword")
	public String forgotPassword(Model model) {
		model.addAttribute("forgotPass", "true");
		return "forgotPass/forgotPassword";
	}
	
	
	@PostMapping("/forgotPassword")
	public String checkNameEmailorUsername(Model model,
			@Valid @ModelAttribute("user") Users user,
			BindingResult result,
			HttpSession session,
			RedirectAttributes attributes) {
		
		String email = request.getParameter("email");
		
		if (email == null || "".equals(email)) {
			model.addAttribute("messageEmailErr", "Not Blank");
			return "forgotPass/forgotPassword";
		}
		
		Users userEmail = usersRepository.findByEmail(email);
		
		if(userEmail != null) {
			session.setAttribute("userForgot", userEmail);
			model.addAttribute("forgotPass","false");
			model.addAttribute("otpVerificationForgotPassword", "true");
		
			String fullName = userEmail.getFirstname() + " " + userEmail.getSurname();
			
			String subject = "Dear" + " " + fullName + "This is OTP to change you account !";
			
			String OTPChangePass = generateRandomNumbers();
			
			sendMailService.sendSimpleEmail(email, subject, OTPChangePass);
			
			session.setAttribute("OTPResetPass", OTPChangePass);
		}else {
			model.addAttribute("forgotPass","true");
			model.addAttribute("otpVerificationForgotPassword", "false");
			model.addAttribute("messageEmailErr", "Not have Email");
			return "forgotPass/forgotPassword";
		}
		
		return "forgotPass/forgotPassword";
	}
	
	@PostMapping("/verify-pass-otp")
	public String checkOTP(Model model,
			@Valid @ModelAttribute("user") Users user,
			BindingResult result,
			HttpSession session,
			RedirectAttributes attributes) {
		
		String OTPChangePass = request.getParameter("OTPChangePass");
		
		String otpResetPass = (String) session.getAttribute("OTPResetPass") ;
		 
		if (OTPChangePass.contains(otpResetPass)) {
			model.addAttribute("forgotPass","false");
			model.addAttribute("otpVerificationForgotPassword", "false");
			model.addAttribute("changePassword", "true");
			
			return "forgotPass/forgotPassword";
		}else {
			
			model.addAttribute("forgotPass","false");
			model.addAttribute("otpVerificationForgotPassword", "true");
			model.addAttribute("changePassword", "false");
			model.addAttribute("messageOTPChangePass","OTP Incorrect");
			return "forgotPass/forgotPassword";
		}
		
	}
	
	
	@PostMapping("/changePassword")
	public String changePass(Model model,
			@Valid @ModelAttribute("user") Users user,
			BindingResult result,
			HttpSession session,
			RedirectAttributes attributes) {
		
		Users usersChangerPass = (Users) session.getAttribute("userForgot") ;
		
		String passChange = request.getParameter("password");
		
		if (passChange == null || "".equals(passChange)) {
			model.addAttribute("forgotPass","false");
			model.addAttribute("otpVerificationForgotPassword", "false");
			model.addAttribute("changePassword", "true");
			model.addAttribute("messagePassword", "Not Blank");
			return "forgotPass/forgotPassword";
		}else {
			usersChangerPass.setPassword(passChange);
			usersRepository.save(usersChangerPass);
			model.addAttribute("user", new Users());
			session.invalidate();
			return "login";
		}
		
	}
	
	
	
	public String generateRandomNumbers() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			int randomNumber = random.nextInt(10); // Tạo số ngẫu nhiên từ 0 đến 9
			sb.append(randomNumber);
		}
		return sb.toString();
	}
	
}
