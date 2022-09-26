package com.web.services.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.entities.Orderdetail;
import com.web.repository.IOrderDetailRepository;
import com.web.services.IOrderDetailService;
@Service
public class OrderDetailService implements IOrderDetailService{
	@Autowired
	IOrderDetailRepository odtrepository;
	@Override
	public List<Orderdetail> findAll() {
		return odtrepository.findAll();
	}

	@Override
	public Optional<Orderdetail> findById(Integer id) {
		Optional<Orderdetail> option = odtrepository.findById(id);
		return option;
	}

	@Override
	public Orderdetail create(Orderdetail orderdetail) {
		odtrepository.save(orderdetail);
		return orderdetail;
	}

	@Override
	public Orderdetail update(Orderdetail orderdetail) {
		odtrepository.save(orderdetail);
		return orderdetail;
	}

	@Override
	public void delete(Integer id) {
		Optional<Orderdetail> delete = odtrepository.findById(id);
		Orderdetail orderdetail = delete.get();
		if (orderdetail !=null) {
			odtrepository.delete(orderdetail);
		}
	}

	@Override
	public List<Orderdetail> orderDetail(Integer ordId) {
		return odtrepository.orderDetail(ordId);
	}
}
