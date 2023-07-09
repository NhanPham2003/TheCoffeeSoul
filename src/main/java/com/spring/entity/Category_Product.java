package com.spring.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category_Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCategory;

	@OneToMany(mappedBy = "category_Product")
	private List<Product> products;

	private String ma_Category;

	private String name_Category;

	private String description_Category;

	private Boolean active_Category;

	public Integer getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(Integer idCategory) {
		this.idCategory = idCategory;
	}

	public String getMa_Category() {
		return ma_Category;
	}

	public void setMa_Category(String ma_Category) {
		this.ma_Category = ma_Category;
	}

	public String getName_Category() {
		return name_Category;
	}

	public void setName_Category(String name_Category) {
		this.name_Category = name_Category;
	}

	public String getDescription_Category() {
		return description_Category;
	}

	public void setDescription_Category(String description_Category) {
		this.description_Category = description_Category;
	}

	public Boolean getActive_Category() {
		return active_Category;
	}

	public void setActive_Category(Boolean active_Category) {
		this.active_Category = active_Category;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
