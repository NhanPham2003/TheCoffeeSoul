package com.spring.controller;

import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.entity.Category_Product;
import com.spring.entity.Detail_Shopping_Cart;
import com.spring.entity.Images_Products;
import com.spring.entity.Product;
import com.spring.entity.Shopping_Cart;
import com.spring.entity.Users;
import com.spring.repository.CategoryRepository;
import com.spring.repository.DetailCardRepositorty;
import com.spring.repository.Images_ProductRepository;
import com.spring.repository.ProductRepository; 
 
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	List<Product> listProduct = new ArrayList<>();
	List<Category_Product> listCategory = new ArrayList<>();
	List<Images_Products> listImage_Product = new ArrayList<>();
	List<Detail_Shopping_Cart> listDSC = new ArrayList<>();
	 
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private Images_ProductRepository imagesRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private DetailCardRepositorty detailSCRepo;
	
	@RequestMapping(value = {"", "/", "/index2", "/home"})
	public String homePage(Model model,
							HttpSession session) {
		Users users =(Users)  session.getAttribute("userLogged");
		
		listCategory = categoryRepo.findAll();
		model.addAttribute("listCategory", listCategory);
		
		listProduct = productRepository.findByActiveProduct(true);
		model.addAttribute("listProduct", listProduct);
		
	
		listImage_Product = imagesRepo.findAll();
		model.addAttribute("listImage_Product", listImage_Product);
		 
		if (users!=null) {
			String fullname = users.getFirstname() + " " + users.getSurname();
			model.addAttribute("messageLoginOrSignin", false);
			model.addAttribute("messageFullName", fullname);
			Integer maKhachHang = users.getMaKhachHang();
			
			listDSC = detailSCRepo.findByMaKhachHangAndTrue(maKhachHang);
			
			int sumProductCart = listDSC.size();
			
			model.addAttribute("sumProductCart", sumProductCart);
			
			session.setAttribute("sumProductCart", sumProductCart);
		}else {
			model.addAttribute("messageLoginOrSignin", true);
		}
		return "index2";
		
		
	}
	
	@GetMapping("/about")
	public String aboutPage(Model model, 
			@ModelAttribute("listAccount") Map<String, String> accs,
			HttpSession session) {
		Users users =(Users)  session.getAttribute("userLogged");
		if (users!=null) {
			int sumProductCart = (Integer) session.getAttribute("sumProductCart");
			model.addAttribute("sumProductCart", sumProductCart);
			String fullname = users.getFirstname() + " " + users.getSurname();
			model.addAttribute("messageLoginOrSignin", false);
			model.addAttribute("messageFullName", fullname);
		}else {
			model.addAttribute("messageLoginOrSignin", true);
		} 
		
		return "about";
	}
	
	@GetMapping("/logout")
	public String logoutUsers(Model model, 
			HttpSession session,
			@ModelAttribute("listAccount") Map<String, String> accs
	) {
		session.invalidate();
		Users user = new Users();
		model.addAttribute("user", user);
		return "login";
	}
	
	
	@ModelAttribute("listAccount")
	public Map<String, String> listModel(){
		Map<String, String> map = new HashMap<>();
		map.put("acc1", "huypn");
		return map;
	}
	
	
	
	
	

}
