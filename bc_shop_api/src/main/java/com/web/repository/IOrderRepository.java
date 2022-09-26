package com.web.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.entities.Order;

public interface IOrderRepository extends JpaRepository<Order, Integer> {
	public List<Order> findAllByordDeleted(byte ordDeleted);
	@Query("SELECT u FROM Order u WHERE u.ordDeleted = 0 AND u.ordId = ?1")
	Optional<Order> findOrderById(Integer proId);
	@Query("SELECT u FROM Order u WHERE u.ordId =(SELECT MAX(ordId) FROM Order)")
	Order getNewOrder();
}
