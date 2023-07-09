package com.spring.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany; 

@Entity
public class RanksUsers implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRanks;
	
	@OneToMany(mappedBy = "ranksUser")
	private List<Users> users;
	
	private String nameRanks;
	
	private String description;
	
	private Integer miniumPopints;
	
	private Boolean active;
	
	
	

	public Integer getIdRanks() {
		return idRanks;
	}

	public void setIdRanks(Integer idRanks) {
		this.idRanks = idRanks;
	}

	public String getNameRanks() {
		return nameRanks;
	}

	public void setNameRanks(String nameRanks) {
		this.nameRanks = nameRanks;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getMiniumPopints() {
		return miniumPopints;
	}

	public void setMiniumPopints(Integer miniumPopints) {
		this.miniumPopints = miniumPopints;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}
	
	
	
	
}
