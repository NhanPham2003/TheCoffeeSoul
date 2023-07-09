package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.entity.Shopping_Cart;

public interface ShoppingCardRepository extends JpaRepository<Shopping_Cart, Integer> {
	
	@Query("SELECT s1 FROM Shopping_Cart s1 WHERE s1.users.maKhachHang = ?1 ")
	Shopping_Cart findMaSCByIDUser(Integer idUser);
}
