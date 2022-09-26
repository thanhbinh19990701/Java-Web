package com.web.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.entities.Order;
import com.web.entities.ResponseObject;
import com.web.services.IOrderService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/order")
public class OrderController {

	@Autowired
	IOrderService iOrderService;
	
	/**
	 * lấy Thông tia của tất các đơn đặt hàng
	 * @return listOrder
	 */
	@GetMapping
	ResponseEntity<ResponseObject> getAllOrder() {

		List<Order> listOrder = iOrderService.getAllOrder();
		if (listOrder != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("OK", "Query Product successfully", listOrder));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ResponseObject("False", "Can Not Element in Order ", ""));

	}
	

	/**
	 * Lấy 1 đơn đặt hàng mới nhất
	 * @return
	 */
	@GetMapping("new")
	public ResponseEntity<ResponseObject> getNewOrder(){
		Order order  = iOrderService.newOrder();
		if(order!= null) {return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("OK", "Query Order successfully", order));
		}
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject("False", "Can Not Element in Order ", ""));
	}
	
	/**
	 * Tạo mới đơn đặt hàng
	 * @param order
	 * @return
	 */
	@PostMapping("/create")
	public ResponseEntity<ResponseObject> create(@RequestBody Order order) {
		// Tạo dữ liệu vào bảng từ đối tượng nhận được
		order = iOrderService.createOrder(order);
		// Tra ve ResponseEntity
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("OK", "Create Comment successfully", order));
	}
	
	/**
	 * Cập nhật trạng thái của đơn hàng
	 * @param order
	 * @param id
	 * @return
	 */
	@PutMapping("/update/{ordId}")
	public ResponseEntity<ResponseObject> update(@RequestBody Order order, @PathVariable("ordId") Integer id) {
		// Lấy comment theo id
		Optional<Order> findorder = iOrderService.findOrderById(id);
		// isPresent dùng để kiểm tra một đối tượng Optional có không rỗng hay không
		// Nếu có tồn tại student, thực hiện update, ngược lại trả về không tìm thấy
		if (findorder.isPresent()) {
			order = iOrderService.updateOrder(order);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("OK", "Update Order successfully", order));
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ResponseObject("OK", "Cannot find Order with id = " + id, ""));
	}
	
	/**
	 * Xóa luận lý Thông tin của đơn hàng
	 * @param id
	 * @return
	 */
	@GetMapping("/delete/{id}")
	public ResponseEntity<ResponseObject> delete(@PathVariable("id") Integer id) {
		// Tìm đối tượng với id
		Optional<Order> findorder = iOrderService.findOrderById(id);
		// Nếu có tồn tại thực hiện xóa
		if (findorder.isPresent()) {
			iOrderService.deleteOrder(id);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("OK", "Delete Order successfully", ""));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ResponseObject("FALSE", "Cannot find Order with id = " + id, ""));
	}

}
