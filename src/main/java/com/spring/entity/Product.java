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
import jakarta.persistence.OneToMany; 

@Entity
public class Product implements Serializable {

	private static final long serialVersionUID = 1L; 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@OneToMany(mappedBy = "product")
	private List<Detail_Shopping_Cart> detailProduct;
	
	@OneToMany(mappedBy = "product")
	private List<DetailBill> detailBill ;
	
	@ManyToOne
	@JoinColumn(name="idCategory")
	private Category_Product category_Product;
	
	@ManyToOne
	@JoinColumn(name="idImage_Product")
	private Images_Products images_Product;
	
	
	
	private String ma_product;
	
	private String name_product;
	
	private String material_product;
	
	private Double price_product;
	
	private Integer remaining_product;
	                         
	private String desciption_product;
	
	private String color_product;
	
	private String weight_product;
	
	@Column(name = "producer_product")
	private String producerProduct;
	
	 @Column(name = "active_product")
	private Boolean activeProduct;
	
	 
	 
	
	public List<Detail_Shopping_Cart> getDetailProduct() {
		return detailProduct;
	}
	public void setDetailProduct(List<Detail_Shopping_Cart> detailProduct) {
		this.detailProduct = detailProduct;
	}
	public String getProducerProduct() {
		return producerProduct;
	}
	public void setProducerProduct(String producerProduct) {
		this.producerProduct = producerProduct;
	}
	public void setActiveProduct(Boolean activeProduct) {
		this.activeProduct = activeProduct;
	}
	public Category_Product getCategory_Product() {
		return category_Product;
	}
	public void setCategory_Product(Category_Product category_Product) {
		this.category_Product = category_Product;
	}
	public Images_Products getImages_Product() {
		return images_Product;
	}
	public void setImages_Product(Images_Products images_Product) {
		this.images_Product = images_Product;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMa_product() {
		return ma_product;
	}
	public void setMa_product(String ma_product) {
		this.ma_product = ma_product;
	}
	public String getName_product() {
		return name_product;
	}
	public void setName_product(String name_product) {
		this.name_product = name_product;
	}
	public String getMaterial_product() {
		return material_product;
	}
	public void setMaterial_product(String material_product) {
		this.material_product = material_product;
	}
	public Double getPrice_product() {
		return price_product;
	}
	public void setPrice_product(Double price_product) {
		this.price_product = price_product;
	}
	public Integer getRemaining_product() {
		return remaining_product;
	}
	public void setRemaining_product(Integer remaining_product) {
		this.remaining_product = remaining_product;
	}
	public String getDesciption_product() {
		return desciption_product;
	}
	public void setDesciption_product(String desciption_product) {
		this.desciption_product = desciption_product;
	}
	public String getColor_product() {
		return color_product;
	}
	public void setColor_product(String color_product) {
		this.color_product = color_product;
	}
	public String getWeight_product() {
		return weight_product;
	}
	public void setWeight_product(String weight_product) {
		this.weight_product = weight_product;
	}
	public String getProducer_product() {
		return producerProduct;
	}
	public void setProducer_product(String producer_product) {
		this.producerProduct = producer_product;
	}
	public Boolean getActiveProduct() {
		return activeProduct;
	}
	public void setActiveproduct(Boolean active_product) {
		this.activeProduct = active_product;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
