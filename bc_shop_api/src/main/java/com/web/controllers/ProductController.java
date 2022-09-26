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

import com.web.entities.Product;
import com.web.entities.ResponseObject;
import com.web.services.IProductService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/product")
public class ProductController {
	
	@Autowired()
	IProductService iProductService;
	
	/**
	 * Lấy thông tin của tất cả các sản phâmr
	 * @return
	 */
	@GetMapping("/list")
	public ResponseEntity<ResponseObject> getAll(){
		List<Product> product = iProductService.getAllProduct();
		if(product!=null){
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("OK", "Get Product successfully", product));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ResponseObject("False", "Can not find Product", ""));
	}
	
	/**
	 * Tìm sản phẩm theo ID
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	ResponseEntity<ResponseObject> findById(@PathVariable Integer id) {
		Optional<Product> adOptional = iProductService.getProductById(id);
		return adOptional.isPresent()
				? ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject("OK", "Query Product successfully", adOptional))
				: ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ResponseObject("False", "Can not find Product with id = " + id, ""));
	}
	
	/**
	 * Tạo sản phẩm mới
	 * @param product
	 * @return
	 */
	@PostMapping("/create")
	public ResponseEntity<ResponseObject> create(@RequestBody Product product) {
		// Tạo dữ liệu vào bảng từ đối tượng nhận được
		product = iProductService.createProduct(product);
		// Tra ve ResponseEntity
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("OK", "Create Product successfully", product));
	}
	
	/**
	 * Cập nhật sản phẩm
	 * @param product
	 * @param id
	 * @return
	 */
	@PutMapping("/update/{proId}")
	public ResponseEntity<ResponseObject> update(@RequestBody Product product, @PathVariable Integer id) {
		// Lấy sinh viên từ DB theo id
		Optional<Product> productObj = iProductService.getProductById(id);
		// isPresent dùng để kiểm tra một đối tượng Optional có không rỗng hay không
		// Nếu có tồn tại student, thực hiện update, ngược lại trả về không tìm thấy
		if (productObj.isPresent()) {
			product = iProductService.updateProduct(product);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("OK", "Update Product successfully", product));
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ResponseObject("OK", "Cannot find Product with id = " + id, ""));
	}
	
	/**
	 * Xóa luận lý sản phẩm theo Id
	 * @param id
	 * @return
	 */
	@GetMapping("/delete/{id}")
	public ResponseEntity<ResponseObject> delete(@PathVariable("id") Integer id) {
		// Tìm đối tượng với id
		Optional<Product> adOptional = iProductService.getProductById(id);
		// Nếu có tồn tại thực hiện xóa
		if (adOptional.isPresent()) {
			iProductService.deleteProduct(id);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("OK", "Delete Product successfully", ""));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ResponseObject("FALSE", "Cannot find Product with id = " + id, ""));
	}
	
	/**
	 * Tìm kiếm sản phẩm theo tên gần giống
	 * @param key
	 * @return
	 */
	@GetMapping("/search/{key}")
	public ResponseEntity<ResponseObject> searchProductByName(@PathVariable("key") String key){
		List<Product> listProduct = iProductService.findProductByName(key);
		if(listProduct != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("OK", "Search Product successfully", listProduct));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ResponseObject("FALSE", "Cannot Search Product with name = " + key, ""));
	}
	
	
	/**
	 * Tìm kiếm sản phẩm theo loại sản phẩm
	 * @param catId
	 * @return
	 */
	@GetMapping("/findCategory/{catId}")
	public ResponseEntity<ResponseObject> findProductByCategory(@PathVariable("catId") Integer catId){
		List<Product> product = iProductService.findProductByCategory(catId);
		if(product!=null){
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("OK", "Get Product successfully", product));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ResponseObject("False", "Can not find Product", ""));
	}
}
