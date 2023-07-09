package com.spring.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detail_shopping_cart")
public class Detail_Shopping_Cart implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Detail_ShoppingCart")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "id_ShoppingCart")
	private Shopping_Cart shop_detail;

	private Integer quantity;

	private Boolean active;

	private String note;
	
	public Detail_Shopping_Cart() {
		// TODO Auto-generated constructor stub
	}
	

	public Detail_Shopping_Cart( Product product, Shopping_Cart shop_detail, Integer quantity,
			Boolean active, String note) {
		super();
		this.product = product;
		this.shop_detail = shop_detail;
		this.quantity = quantity;
		this.active = active;
		this.note = note;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Shopping_Cart getShop_detail() {
		return shop_detail;
	}

	public void setShop_detail(Shopping_Cart shop_detail) {
		this.shop_detail = shop_detail;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
