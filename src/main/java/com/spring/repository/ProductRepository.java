package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	List<Product> findByActiveProduct(Boolean activeProduct);

	@Query("SELECT DISTINCT p.producerProduct FROM Product p where activeProduct=1")
    List<String> findDistinctCategories();
	
	int countByActiveProduct(Boolean activeProduct);

	List<Product> findByproducerProduct(String producerProduct);
	
	Product findByid(Integer id);
}
