package com.web.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.entities.User;

public interface IUserRepository extends JpaRepository<User, Integer>{
	 public User findByUsername(String username);
	 public Optional<User> findById(Integer Id);
}
