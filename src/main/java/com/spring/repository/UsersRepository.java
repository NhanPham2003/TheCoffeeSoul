package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer>{

	Users findByUsernameAndPassword(String username, String password);

	Users findByEmailAndPassword(String email, String password);

	Users findByEmail(String email);

	Users findByUsername(String username);


	
	

	

}
