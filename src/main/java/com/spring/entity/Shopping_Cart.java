package com.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "shopping_cart")
public class Shopping_Cart implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="maShoppingCart")
	private Integer maShoppingCart;

	@OneToMany(mappedBy = "shop_detail")
	private List<Detail_Shopping_Cart> detailShop;

	@OneToOne
	@JoinColumn(name = "users_maKhachHang")
	private Users users;

	private Date dateCreate;

	private Boolean active;

	private Date dateUpdate;
	
	

	public Shopping_Cart() {  
	}

	public Shopping_Cart( Users users, Date dateCreate, Boolean active,
			Date dateUpdate) {
		this.users = users;
		this.dateCreate = dateCreate;
		this.active = active;
		this.dateUpdate = dateUpdate;
	}

	public Integer getMaShoppingCart() {
		return maShoppingCart;
	}

	public void setMaShoppingCart(Integer maShoppingCart) {
		this.maShoppingCart = maShoppingCart;
	}

	public List<Detail_Shopping_Cart> getDetailShop() {
		return detailShop;
	}

	public void setDetailShop(List<Detail_Shopping_Cart> detailShop) {
		this.detailShop = detailShop;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Date getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
