package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.Images_Products;

public interface Images_ProductRepository extends JpaRepository<Images_Products, Integer>{

	Images_Products findByIdImage(Integer idImage);
}
