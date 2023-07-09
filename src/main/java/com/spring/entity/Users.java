package com.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class Users implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "maKhachHang")
	private Integer maKhachHang;
	
	@ManyToOne
	@JoinColumn(name="ranksUser_idRanks")
	private RanksUsers ranksUser;
	
	@ManyToOne
	@JoinColumn(name="idRoleUser")
	private RoleUsers roleUser;
	
	@OneToMany(mappedBy = "user")
	private List<Bill> bill;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "users")
    private Shopping_Cart shopping_cart;
	
	private String surname;
	
	private String firstname;
	
	private Date dateOfBirth;
	
	private String address;
	
	private String phone;
	
	private String email;
	
	private Boolean gender;
	
	private String CCCD;
	
	private String avartar;
	
	private Boolean active = false;
	
	private Integer points = 0 ;
	
	private String username;
	
	@NotBlank
	private String password;

	public Integer getMaKhachHang() {
		return maKhachHang;
	}

	public void setMaKhachHang(Integer maKhachHang) {
		this.maKhachHang = maKhachHang;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public String getCCCD() {
		return CCCD;
	}

	public void setCCCD(String cCCD) {
		CCCD = cCCD;
	}

	public String getAvartar() {
		return avartar;
	}

	public void setAvartar(String avartar) {
		this.avartar = avartar;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public RanksUsers getRanksUser() {
		return ranksUser;
	}

	public void setRanksUser(RanksUsers ranksUser) {
		this.ranksUser = ranksUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RoleUsers getRoleUser() {
		return roleUser;
	}

	public void setRoleUser(RoleUsers roleUser) {
		this.roleUser = roleUser;
	}

	public List<Bill> getBill() {
		return bill;
	}

	public void setBill(List<Bill> bill) {
		this.bill = bill;
	}

	public Shopping_Cart getShopping_cart() {
		return shopping_cart;
	}

	public void setShopping_cart(Shopping_Cart shopping_cart) {
		this.shopping_cart = shopping_cart;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
