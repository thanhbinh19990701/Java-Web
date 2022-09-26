package com.web.services;

import java.util.List;

import com.web.models.Order;
import com.web.models.ResponseObject;

public interface IOderService {
	public ResponseObject<Order> createOrder(Order order,String cookie);
	public ResponseObject<Order> getNewOrder(String cookie);
	public ResponseObject<List<Order>> getAllOrder(String cookie);
	public ResponseObject<Order> update(Order order,Integer id,String cookie);
	public void deleteOrder(Integer ordId,String cookie);
}
