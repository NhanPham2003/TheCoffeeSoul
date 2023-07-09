package com.spring.entity;

public class QuantityDSC {

	private Integer idProduct;
	private Integer quantityProduct;
	
	public QuantityDSC() {
		// TODO Auto-generated constructor stub
	}

	public QuantityDSC(Integer idProduct, Integer quantityProduct) {
		super();
		this.idProduct = idProduct;
		this.quantityProduct = quantityProduct;
	}

	public Integer getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Integer idProduct) {
		this.idProduct = idProduct;
	}

	public Integer getQuantityProduct() {
		return quantityProduct;
	}

	public void setQuantityProduct(Integer quantityProduct) {
		this.quantityProduct = quantityProduct;
	}
	
}
