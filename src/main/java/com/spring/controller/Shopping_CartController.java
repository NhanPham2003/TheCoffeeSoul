package com.spring.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.entity.Bill;
import com.spring.entity.DetailBill;
import com.spring.entity.Detail_Shopping_Cart;
import com.spring.entity.Product;
import com.spring.entity.QuantityDSC;
import com.spring.entity.Shopping_Cart;
import com.spring.entity.Users;
import com.spring.repository.BillRepository;
import com.spring.repository.DetailBillRepository;
import com.spring.repository.DetailCardRepositorty;
import com.spring.repository.ProductRepository;
import com.spring.repository.ShoppingCardRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class Shopping_CartController {

	private List<Detail_Shopping_Cart> listDSC = new ArrayList<>();
	
	@Autowired 
	private BillRepository billRepo;
	
	@Autowired
	private ShoppingCardRepository SCRepo;
	
	@Autowired
	private DetailCardRepositorty detailCardRepo;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private DetailBillRepository detailBillRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	
	@GetMapping("/shopping-cart")

	public String shoppingCartPage(Model model,
								   HttpSession session) {
		
		Users users =(Users)  session.getAttribute("userLogged");
		
		String fullname = users.getFirstname() + " " + users.getSurname();
		
		model.addAttribute("messageLoginOrSignin", false);
		
		model.addAttribute("messageFullName", fullname);
		
		int sumProductCart = (Integer) session.getAttribute("sumProductCart");
		model.addAttribute("sumProductCart", sumProductCart);
		
		Shopping_Cart shoppingCart = SCRepo.findMaSCByIDUser(users.getMaKhachHang());
		
		Detail_Shopping_Cart dsc = detailCardRepo.findByProduct_IdAndShop_detail_Id_ShoppingCart(users.getMaKhachHang(), shoppingCart.getMaShoppingCart());
		

		Integer maKhachHang = users.getMaKhachHang();
		
		listDSC = detailCardRepo.findByMaKhachHangAndTrue(maKhachHang);
		model.addAttribute("listDSC", listDSC);
		
		return "shopping-cart";
	}
	
	@PostMapping("/deleteToCartButton/{idProduct}")
	public String deleteToCartButton(@PathVariable("idProduct") Integer idProduct) {
		
		Shopping_Cart shoppingCarts = (Shopping_Cart) session.getAttribute("shoppingCartLogged");
		
		Detail_Shopping_Cart dsc = detailCardRepo.findByProduct_IdAndShop_detail_Id_ShoppingCart(shoppingCarts.getMaShoppingCart(), idProduct);
		
		dsc.setActive(false);
		
		dsc.setQuantity(0);
		
		detailCardRepo.save(dsc);
		
		listDSC = detailCardRepo.findByMaKhachHangAndTrue(shoppingCarts.getUsers().getMaKhachHang());
		
		int sumProductCartNew = listDSC.size();
		
		session.removeAttribute("sumProductCart");
		
		session.setAttribute("sumProductCart", sumProductCartNew);
	    
		return "redirect:/shopping-cart";
	}
	
	@PostMapping("/checkout")
	public String checkOut(Model model) {
		
		Users users =(Users)  session.getAttribute("userLogged");
		
		Date date = new Date();
		
		Bill bill = new Bill();
		bill.setDateCreate(date);
		bill.setDatePay(null);
		bill.setNote("note");
		bill.setStatus(false);
		bill.setStatusDH("Wait for pay");
		bill.setTotal(0.0);
		bill.setUser(users);
		
		billRepo.save(bill);
		session.removeAttribute("idBill");
		session.setAttribute("idBill", bill);
		
		
		List<Detail_Shopping_Cart> listDSP = detailCardRepo.findByMaKhachHangAndTrue(users.getMaKhachHang());
		
		for (Detail_Shopping_Cart x : listDSP) {
			DetailBill detailBill = new DetailBill();
			detailBill.setBill(bill);
			detailBill.setNote("Che ten");
			detailBill.setProduct(x.getProduct());
			detailBill.setQuantity(x.getQuantity());
			detailBill.setStatus(true);
			detailBill.setTotalPrice(x.getProduct().getPrice_product() * x.getQuantity());
			
			detailBillRepo.save(detailBill);
			
			int number = x.getProduct().getRemaining_product();
			
			Product product = productRepo.findByid(x.getProduct().getId());
			System.out.println(number);
			
			product.setRemaining_product(number - x.getQuantity());
			
			productRepo.save(product);
			
			x.setQuantity(0);
			x.setActive(false);
			detailCardRepo.save(x);
		}
		session.removeAttribute("sumProductCart");
		
		listDSC = detailCardRepo.findByMaKhachHangAndTrue(users.getMaKhachHang());
		int sumProductCartNew = listDSC.size();
		session.setAttribute("sumProductCart", sumProductCartNew);
		model.addAttribute("sumProductCart", sumProductCartNew);
		
		String fullname = users.getFirstname() + " " + users.getSurname();
		model.addAttribute("messageLoginOrSignin", false);
		model.addAttribute("messageFullName", fullname);
		
		Integer idBill = bill.getId();
		
		List<DetailBill> detailBill = detailBillRepo.getAllByIdBill(idBill);		
		model.addAttribute("listDSC", detailBill );
		model.addAttribute("users", users);
		
		Double sum = detailBillRepo.findByIdBill(bill.getId());
		model.addAttribute("toltal", sum);
		
		return "checkout";
	}
	
	@PostMapping("/placeOrder")
	public String placeOrder(Model mode) {
		Bill bill = (Bill) session.getAttribute("idBill");
		
		Double sum = detailBillRepo.findByIdBill(bill.getId());
		
		Date date = new Date();
		
		bill.setDatePay(date);
		bill.setStatus(true);
		bill.setStatusDH("Pay Succes");
		bill.setTotal(sum);
		
		billRepo.save(bill);
		
		
		return "redirect:/home";
	}

	
}
