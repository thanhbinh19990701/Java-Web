package com.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.entities.Orderdetail;

public interface IOrderDetailRepository extends JpaRepository<Orderdetail, Integer>{
	@Query("SELECT u FROM Orderdetail u WHERE  u.odtDeleted = 0 AND u.order.ordId = ?1")
	List<Orderdetail> orderDetail(Integer ordId);
}
