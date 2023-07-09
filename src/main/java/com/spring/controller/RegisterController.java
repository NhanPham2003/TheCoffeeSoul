package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.entity.RoleUsers;
import com.spring.entity.Users;
import com.spring.repository.RoleUserRepository;
import com.spring.repository.UsersRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import java.util.Random;

@Controller
public class RegisterController {

	private Random random = new Random();

	@Autowired
	HttpServletRequest request;

	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private RoleUserRepository roleUserRepo;

	@Autowired
	private SendMailServices sendMailService;

	@GetMapping("/register")
	public String getRegisterForm(Model model) {
		model.addAttribute("Register", true);
		model.addAttribute("user", new Users());
		return "register";
	}

	@PostMapping("/register")
	public String checkRegister(Model model,
			@Valid @ModelAttribute("user") Users user,
			BindingResult result,
			HttpSession session,
			RedirectAttributes attributes) {

		String firstName = request.getParameter("firstname");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		String confirmPass = request.getParameter("confirmPass");

		boolean hasErr = false;

		if (firstName == null || "".equals(firstName)) {
			model.addAttribute("messageFirstnameErr", "Not Blank");
			hasErr = true;
		}

		if (email == null || "".equals(email)) {
			model.addAttribute("messageEmailErr", "Please enter your Email");
			hasErr = true;
		}

		if (confirmPass == null || "".equals(confirmPass)) {
			model.addAttribute("messageCofirmPassErr", "Please enter ConfirmPassword");
			hasErr = true;
		}
		
		if (hasErr) {
			model.addAttribute("Register", true);
			return "register";

		}
		
		Users userEmail = usersRepository.findByEmail(email);

		if(userEmail != null) {
			hasErr = true;
		}
		if (hasErr) {
			model.addAttribute("messageFirstname", firstName);
			model.addAttribute("messageSurname", surname);
			model.addAttribute("messageEmail", email);
			model.addAttribute("messagePassword", pass);
			model.addAttribute("messageEmailErr", "Had Email !");
			model.addAttribute("Register", true);
			return "register";

		}
		if (result.hasErrors()) {
			model.addAttribute("otpVerificationRequired", true);
			return "register";
		}
		
		String numberOTP = generateRandomNumbers();
		
		
		
		if(userEmail == null) {
			RoleUsers role = roleUserRepo.findById(2).orElse(null);
			user.setRoleUser(role);
			
			Users userFormDB = usersRepository.save(user);
			
			session.setAttribute("userLogged", userFormDB);
			
			session.setAttribute("otp", numberOTP);
			
			String subject="Dear" + " " + firstName + " " + surname + " " + "This is OTP to register a your account : ";

			sendMailService.sendSimpleEmail(email, "Verify your email address"  ,subject + " "+  numberOTP);
			
			model.addAttribute("Register", false);
			
			model.addAttribute("otpVerificationRequired", true);
			
		}
		
		return "register";
	}
	
	@GetMapping("/resened-otp")
	public String reSendOTPEmail( HttpSession session, Model model) {
		
		model.addAttribute("Register", false);
		
		model.addAttribute("otpVerificationRequired", true);
		
		Users userLogged = (Users) session.getAttribute("userLogged");
		
		String numberOTP = generateRandomNumbers();
		
		String firstName = userLogged.getFirstname();
		
		String surname = userLogged.getSurname();
		
		String email = userLogged.getEmail();
		
		session.removeAttribute("otp");
		
		session.setAttribute("otp", numberOTP);
		
		String subject="Dear" + " " + firstName + " " + surname + " " + "This is OTP to register a your account : ";

		sendMailService.sendSimpleEmail(email, "Verify your email address"  ,subject + " "+  numberOTP);
		
		
		return "register";
	}

	@PostMapping("/resened-otp")
	public String reSendOTPEmailSecond( HttpSession session, Model model) {
		
		String OTPRegister = request.getParameter("OTPRegister");
		
		Users userLogged = (Users) session.getAttribute("userLogged");
	    String otp = (String) session.getAttribute("otp");
		
		if (otp.equals(OTPRegister)) {
            userLogged.setActive(true);
            usersRepository.save(userLogged);
            model.addAttribute("user", new Users());
            session.invalidate();
            return "login";
        } else {
            // Mã OTP không khớp, hiển thị thông báo lỗi
        	model.addAttribute("Register", false);
        	model.addAttribute("otpVerificationRequired", true);
            model.addAttribute("messageOTP", "Invalid OTP. Please try again.");
            return "register";
        }
	}
	
	@PostMapping("/verify-otp")
	public String sendOTPEmail( HttpSession session, Model model) {
		
		String OTPRegister = request.getParameter("OTPRegister");
		
		Users userLogged = (Users) session.getAttribute("userLogged");
	    String otp = (String) session.getAttribute("otp");
		
		if (otp.equals(OTPRegister)) {
            userLogged.setActive(true);
            usersRepository.save(userLogged);
            model.addAttribute("user", new Users());
            session.invalidate();
            return "login";
        } else {
            // Mã OTP không khớp, hiển thị thông báo lỗi
        	model.addAttribute("Register", false);
        	model.addAttribute("otpVerificationRequired", true);
            model.addAttribute("messageOTP", "Invalid OTP. Please try again.");
            return "register";
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
