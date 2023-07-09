package com.spring.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.entity.Category_Product;
import com.spring.entity.Images_Products;
import com.spring.entity.Product;
import com.spring.entity.Shopping_Cart;
import com.spring.entity.Users;
import com.spring.repository.CategoryRepository;
import com.spring.repository.ProductRepository;
import com.spring.repository.ShoppingCardRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class ProductController {
	
	List<Product> listProduct = new ArrayList<>();
	List<Category_Product> listCategory = new ArrayList<>();
	List<Images_Products> listImage_Product = new ArrayList<>();
	 
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@GetMapping("/shop")
	public String productPage(Model model,
			RedirectAttributes attributes,
			HttpSession session) {
			
		
		listCategory = categoryRepo.findAll();
		model.addAttribute("listCategory", listCategory);

		List<String> listBrand = productRepository.findDistinctCategories();
		model.addAttribute("listBrand", listBrand);
		
		int sumProduct = productRepository.countByActiveProduct(true);
		model.addAttribute("sumProduct", sumProduct);
		
		Users users =(Users)  session.getAttribute("userLogged");
		   
		if (users!=null) {
			String fullname = users.getFirstname() + " " + users.getSurname();
			model.addAttribute("messageLoginOrSignin", false);
			model.addAttribute("messageFullName", fullname);
			int sumProductCart = (Integer) session.getAttribute("sumProductCart");
			model.addAttribute("sumProductCart", sumProductCart);
		}else {
			model.addAttribute("messageLoginOrSignin", true);
		}
		
		listProduct = productRepository.findByActiveProduct(true);
		model.addAttribute("list", listProduct);
		return "shop";
	}
	
	@GetMapping("/shop-details/{id}")
	public String detailPage(Model model,
			@PathVariable(name = "id") Integer productId,
			RedirectAttributes attributes,
			HttpSession session
		) {
		Users users =(Users)  session.getAttribute("userLogged");
		if (users!=null) {
			String fullname = users.getFirstname() + " " + users.getSurname();
			model.addAttribute("messageLoginOrSignin", false);
			model.addAttribute("messageFullName", fullname);
			int sumProductCart = (Integer) session.getAttribute("sumProductCart"); 
			model.addAttribute("sumProductCart", sumProductCart);
		}else {
			model.addAttribute("messageLoginOrSignin", true);
		}

		Product productDetail = productRepository.findByid(productId);
		
		model.addAttribute("product", productDetail);
		
		model.addAttribute("idProduct", productId);
		 
		session.removeAttribute("idProductDetail");
		
		session.setAttribute("idProductDetail", productId.toString());
		
		return "shop-details";
	}
	
	@GetMapping("/{a}")
	public String showBrandProduct(Model model,
			@PathVariable(name = "a") String brand,
			RedirectAttributes attributes,
			HttpSession session
		) { 
		
		Users users =(Users)  session.getAttribute("userLogged");
		if (users!=null) {
			String fullname = users.getFirstname() + " " + users.getSurname();
			model.addAttribute("messageLoginOrSignin", false);
			model.addAttribute("messageFullName", fullname);
		}else {
			model.addAttribute("messageLoginOrSignin", true);
		}
		
		listCategory = categoryRepo.findAll();
		model.addAttribute("listCategory", listCategory);
		
		
		List<String> listBrand = productRepository.findDistinctCategories();
		model.addAttribute("listBrand", listBrand);
		
		
		listProduct = productRepository.findByproducerProduct(brand);
		
		model.addAttribute("list", listProduct);
		
		int sumProduct = listProduct.size();
		model.addAttribute("sumProduct", sumProduct);
		
		return "/shop";
	}
	

}
