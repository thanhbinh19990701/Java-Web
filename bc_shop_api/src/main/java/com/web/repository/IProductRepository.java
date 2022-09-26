package com.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.entities.Product;

public interface IProductRepository extends JpaRepository<Product, Integer>{
	public List<Product> findAllByproDeleted(byte proDeleted);
	
	@Query("SELECT u FROM Product u WHERE u.proDeleted = 0 AND u.proName LIKE %?1%")
	List<Product> findProductByName(String proName);
	
	
	@Query("SELECT u FROM Product u WHERE  u.proDeleted = 0 AND u.category.catId = ?1")
	List<Product> findProductByCategory(Integer cartId);
}