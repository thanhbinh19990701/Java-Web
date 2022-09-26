package com.web.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.entities.Orderdetail;
import com.web.entities.ResponseObject;
import com.web.entities.User;
import com.web.services.IOrderDetailService;
import com.web.services.IUserService;

@RestController
@RequestMapping("/orderdetail")
public class OrderDetailController {
	@Autowired
	IOrderDetailService dtoservice;

	@Autowired
	ServletContext servletContext;
	@Autowired
	IUserService iUserService;
	
	/**
	 * lấy chi tiết đơn hàng theo id
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	ResponseEntity<ResponseObject> findById(@PathVariable Integer id) {
		Optional<Orderdetail> adOptional = dtoservice.findById(id);
		return adOptional.isPresent()
				? ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject("OK", "Query Object successfully", adOptional))
				: ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject("False", "Can not find Orderdetail with id = " + id, ""));
	}

//	Insert data
	/**
	 * Với REST API, dữ liệu khi trả về sẽ nằm trong response body, dạng JSON Status
	 * code: một con số báo response có thành công hay không. Ví dụ 200 là OK, 401
	 * là không được xác thực, 403 là không đủ quyền, 404 là không tìm thấy. Header:
	 * tương tự request, response cũng có các header Body: trả về thêm 1 số thông
	 * báo, hay dữ liệu. Thì lúc này dữ liệu sẽ được chứa trong response body.
	 * 
	 * @param orderdetail
	 * @return
	 */
	@PostMapping("/add")
	public ResponseEntity<ResponseObject> create(@RequestBody Orderdetail orderdetail) {
		// Tạo dữ liệu vào bảng từ đối tượng nhận được
		orderdetail = dtoservice.create(orderdetail);
		// Tra ve ResponseEntity
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("OK", "Create orderdetail successfully", orderdetail));
	}

//	
	/**
	 * Update orderdetail 
	 * @param orderdetail
	 * @param id
	 * @return
	 */
	@PutMapping("/{id}")
	public ResponseEntity<ResponseObject> update(@RequestBody Orderdetail orderdetail, @PathVariable Integer id) {
		// Lấy orderdetail từ DB theo id
		Optional<Orderdetail> studentOpt = dtoservice.findById(id);
		// isPresent dùng để kiểm tra một đối tượng Optional có không rỗng hay không
		// Nếu có tồn tại orderdetail, thực hiện update, ngược lại trả về không tìm thấy
		if (studentOpt.isPresent()) {
			orderdetail.getOdtId();
			orderdetail = dtoservice.update(orderdetail);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("OK", "Update orderdetail successfully", orderdetail));
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ResponseObject("OK", "Cannot find orderdetail with id = " + id, ""));
	}

	/**
	 * xóa chi tiết đơn hàng theo id
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseObject> delete(@PathVariable("id") Integer id) {
		// Tìm đối tượng với id
		Optional<Orderdetail> adOptional = dtoservice.findById(id);
		// Nếu có tồn tại thực hiện xóa
		if (adOptional.isPresent()) {
			dtoservice.delete(id);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("OK", "Delete orderdetail successfully", ""));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ResponseObject("FALSE", "Cannot find orderdetail with id = " + id, ""));
	}
	
	/**
	 * lấy chi tiết đơn hàng theo id của đơn hàng
	 * @param ordId
	 * @return
	 */
	@GetMapping("/detail/{ordId}")
	public  ResponseEntity<ResponseObject> orderDetail(@PathVariable("ordId") Integer ordId){
		List<Orderdetail> listOrderDetail = dtoservice.orderDetail(ordId);
		if(listOrderDetail!=null) {
			 return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("OK", "Get OrderDetail successfully", listOrderDetail));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
		.body(new ResponseObject("FALSE", "Cannot Get OrderDetail", ""));
	}
	
	/**
	 * Lấy thông tin của user theo ID
	 * @param id
	 * @return
	 */
	@GetMapping("/user/{id}")
	public ResponseEntity<ResponseObject> findUser(@PathVariable("id") Integer id){
		Optional<User> user = iUserService.findById(id);
		if(user.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "sucess", user.get()));	
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseObject("Failed", "not sucess",""));
	}
}
