package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.Shopping_Cart;

public interface ShoppingCartRepository extends JpaRepository<Shopping_Cart, Integer> {

	
}
