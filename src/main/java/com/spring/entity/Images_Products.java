package com.spring.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Images_Products implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idImage_Product")
	private Integer idImage;
	
	
	@OneToMany(mappedBy = "images_Product")
	private List<Product> products;
	
	
	private String image_Product_1;
	
	private String image_Product_2;
	
	private String image_Product_3;
	
	private String image_Product_4;
	
	private String image_Product_5;

	public Integer getIdImage() {
		return idImage;
	}

	public void setIdImage_Product(Integer idImage_Product) {
		this.idImage = idImage_Product;
	}

	public String getImage_Product_1() {
		return image_Product_1;
	}

	public void setImage_Product_1(String image_Product_1) {
		this.image_Product_1 = image_Product_1;
	}

	public String getImage_Product_2() {
		return image_Product_2;
	}

	public void setImage_Product_2(String image_Product_2) {
		this.image_Product_2 = image_Product_2;
	}

	public String getImage_Product_3() {
		return image_Product_3;
	}

	public void setImage_Product_3(String image_Product_3) {
		this.image_Product_3 = image_Product_3;
	}

	public String getImage_Product_4() {
		return image_Product_4;
	}

	public void setImage_Product_4(String image_Product_4) {
		this.image_Product_4 = image_Product_4;
	}

	public String getImage_Product_5() {
		return image_Product_5;
	}

	public void setImage_Product_5(String image_Product_5) {
		this.image_Product_5 = image_Product_5;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
