package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.entity.DetailBill;
import com.spring.entity.Detail_Shopping_Cart; 
import com.spring.entity.QuantityDSC;

public interface DetailCardRepositorty extends JpaRepository<Detail_Shopping_Cart, Integer> {

	@Query("SELECT d FROM Detail_Shopping_Cart d JOIN d.shop_detail c WHERE c.users.maKhachHang = ?1 AND d.active = true ")
    List<Detail_Shopping_Cart> findByMaKhachHangAndTrue(Integer maKhachHang);
	
    
    @Query("SELECT d FROM Detail_Shopping_Cart d WHERE d.shop_detail.maShoppingCart = ?1 and  d.product.id = ?2")
    Detail_Shopping_Cart findByProduct_IdAndShop_detail_Id_ShoppingCart(Integer maSC, Integer idProduct);


	void save(DetailBill detailBill);

}
