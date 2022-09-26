package com.web.services;

import java.util.List;
import java.util.Optional;

import com.web.entities.Order;

public interface IOrderService {
	/**
	 * Lấy tất cả các đơn hàng
	 * @return
	 */
	public List<Order> getAllOrder();
	/**
	 * tìm đơn hàng theo ID
	 * @param ordId
	 * @return
	 */
	public Optional<Order> findOrderById(Integer ordId);
	/**
	 * Tạo mới đơn hàng
	 * @param order
	 * @return
	 */
	public Order createOrder(Order order);
	/**
	 * Cập nhật đơn hàng
	 * @param order
	 * @return
	 */
	public Order updateOrder(Order order);
	/**
	 * Xóa đơn hàng
	 * @param ordId
	 */
	public void deleteOrder(Integer ordId);
	/**
	 * Lấy thông tin đơn hàng mới nhất
	 * @return
	 */
	public Order newOrder();
}
