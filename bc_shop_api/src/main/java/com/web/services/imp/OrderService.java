package com.web.services.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.entities.Order;
import com.web.repository.IOrderRepository;
import com.web.services.IOrderService;
@Service
public class OrderService implements IOrderService{

	@Autowired
	IOrderRepository iOrderRepository;
	
	@Override
	public List<Order> getAllOrder() {
		// TODO Auto-generated method stub
		return iOrderRepository.findAllByordDeleted((byte)0);
	}

	@Override
	public Optional<Order> findOrderById(Integer ordId) {
		// TODO Auto-generated method stub
		return iOrderRepository.findOrderById(ordId);
	}
	
	@Override
	public Order createOrder(Order order) {
		// TODO Auto-generated method stub
		return iOrderRepository.save(order);
	}

	@Override
	public Order updateOrder(Order order) {
		// TODO Auto-generated method stub
		return iOrderRepository.save(order);
	}

	@Override
	public void deleteOrder(Integer ordId) {
		// TODO Auto-generated method stub
		Optional<Order> delete = iOrderRepository.findOrderById(ordId);
		if(delete.isPresent()) {
			Order order = delete.get();
			order.setOrdDeleted((byte)1);
			iOrderRepository.save(order);
		}
	}

	@Override
	public Order newOrder() {
		// TODO Auto-generated method stub
		return iOrderRepository.getNewOrder();
	}


}
