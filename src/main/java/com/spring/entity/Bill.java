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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany; 

@Entity
public class Bill implements Serializable {

	private static final long serialVersionuid = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idBill")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "maKhachHang")
	private Users user;
	
	@OneToMany(mappedBy = "bill")
	List<DetailBill> detailBill;
	
	private Date dateCreate;
	private String note;
	private Date datePay;
	private Boolean status;
	private String statusDH;
	private Double total;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Date getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getDatePay() {
		return datePay;
	}
	public void setDatePay(Date datePay) {
		this.datePay = datePay;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	public List<DetailBill> getDetailBill() {
		return detailBill;
	}
	public void setDetailBill(List<DetailBill> detailBill) {
		this.detailBill = detailBill;
	}
	public String getStatusDH() {
		return statusDH;
	}
	public void setStatusDH(String statusDH) {
		this.statusDH = statusDH;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public static long getSerialversionuid() {
		return serialVersionuid;
	} 
}
