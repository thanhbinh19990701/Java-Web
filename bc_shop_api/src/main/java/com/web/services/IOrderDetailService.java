package com.web.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.web.entities.Orderdetail;
@Service
public interface IOrderDetailService {
	/**
	 * Lấy tất cả chi tiết Đơn hàng 
	 * @return
	 */
	List<Orderdetail> findAll();
	/**
	 * Lấy chi tiết Đơn hàng theo ID
	 * @param id
	 * @return
	 */
	Optional<Orderdetail> findById(Integer id);
	/**
	 * Tạo mới chi tiết Đơn hàng
	 * @param orderdetail
	 * @return
	 */
	Orderdetail create(Orderdetail orderdetail);
	/**
	 * Cập nhật chi tiết Đơn hàng
	 * @param orderdetail
	 * @return
	 */
	Orderdetail update(Orderdetail orderdetail);
	/**
	 * Xóa chi Đơn hàng tiết theo ID
	 * @param id
	 */
	void delete(Integer id);
	/**
	 * Lấy chi tiết của Đơn hàng theo ID đơn
	 * @param ordId
	 * @return
	 */
	List<Orderdetail> orderDetail(Integer ordId);
}
