package com.spring.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.spring.entity.DetailBill;

public interface DetailBillRepository extends JpaRepository<DetailBill, Integer> {

	@Query("SELECT SUM(d.totalPrice) FROM DetailBill d WHERE d.bill.id =?1 ")
    Double findByIdBill(Integer idBill);
	
	@Query("SELECT d FROM DetailBill d WHERE d.bill.id =?1 ")
    List<DetailBill> getAllByIdBill(Integer idBill);
}
