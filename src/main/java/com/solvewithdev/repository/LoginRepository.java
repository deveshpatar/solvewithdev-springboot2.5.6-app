package com.solvewithdev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.solvewithdev.entity.UserLogin;

public interface LoginRepository extends JpaRepository<UserLogin, String>{

}
