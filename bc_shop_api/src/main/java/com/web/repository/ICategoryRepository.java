package com.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.entities.Category;

public interface ICategoryRepository extends JpaRepository<Category, Integer> {

}
