package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.RoleUsers;

public interface RoleUserRepository extends JpaRepository<RoleUsers, Integer> {

}
