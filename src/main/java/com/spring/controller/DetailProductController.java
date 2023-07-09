package com.spring.controller;
 
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam; 

import com.spring.entity.Detail_Shopping_Cart;
import com.spring.entity.Product;
import com.spring.entity.Shopping_Cart;
import com.spring.entity.Users;
import com.spring.repository.DetailCardRepositorty;
import com.spring.repository.ProductRepository;
import com.spring.repository.ShoppingCardRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class DetailProductController {

	List<Detail_Shopping_Cart> listDSC= new ArrayList<>();
	
	@Autowired
	HttpServletRequest request;
	 
	@Autowired
	private DetailCardRepositorty DSPRepo;
	
	@Autowired
	private ShoppingCardRepository SCRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
    private HttpSession session;
	
	
	@PostMapping("/addToCart")
	public ResponseEntity<String> addToCart(Model model,
	                                        @RequestParam("quantity") Integer quantity) {
	   
	    String id = (String) session.getAttribute("idProductDetail");
	    
	    int idProduct = Integer.parseInt(id);
	    
	    Product product = productRepo.findByid(idProduct);
	    
	    Users users =(Users)  session.getAttribute("userLogged");
	    
		if (users != null) {
			
			int idUserLogged = users.getMaKhachHang();
		    
		    Shopping_Cart shoppingCart = SCRepo.findMaSCByIDUser(idUserLogged);
		    
		    int maSC = shoppingCart.getMaShoppingCart();
			
			Detail_Shopping_Cart dscOld = DSPRepo.findByProduct_IdAndShop_detail_Id_ShoppingCart(maSC, idProduct);
			
			if (dscOld != null) {
				
				int quantityNew = quantity + dscOld.getQuantity();
				
				dscOld.setQuantity(quantityNew);
				
				dscOld.setActive(true);
				
				DSPRepo.save(dscOld);
				
				listDSC = DSPRepo.findByMaKhachHangAndTrue(idProduct);
				 
				session.setAttribute(id, dscOld);
				
				
				return ResponseEntity.ok("Product added to cart successfully");
			}else {
				
				Detail_Shopping_Cart  dscNew = new Detail_Shopping_Cart(product, shoppingCart, quantity, true, null);
				
				int sumProductCart = (Integer) session.getAttribute("sumProductCart");
				
				session.removeAttribute("sumProductCart");
				
				int quantityNew = quantity + sumProductCart;
				
				session.setAttribute("sumProductCart", quantityNew);
				
				DSPRepo.save(dscNew);
				
				return ResponseEntity.ok("Product added to cart successfully");
			}
			
		}else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please login to add the product to cart");
		}
	    
	}

}
