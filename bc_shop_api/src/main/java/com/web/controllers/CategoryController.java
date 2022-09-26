package com.web.controllers;

import java.util.List;
import java.util.Optional;

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

import com.web.entities.Category;
import com.web.entities.ResponseObject;
import com.web.services.ICategoryService;
@RestController
@RequestMapping("/category")
public class CategoryController {

		
		@Autowired
		ICategoryService categoryService;
		
		@GetMapping("/list")
		List<Category> getAll(){
			return categoryService.findAll();
		}
		
		/**
		 * ResponseEntity sẽ cung cấp cho một số tính linh hoạt bổ sung trong việc xác định các tiêu đề phản hồi HTTP tùy ý
		 * @param id
		 * @return
		 */
		@GetMapping("/{id}")
		ResponseEntity<ResponseObject> findById(@PathVariable Integer id) {
			Optional<Category> adOptional = categoryService.findById(id);
			return adOptional.isPresent()?
					 ResponseEntity.status(HttpStatus.OK).body(
								new ResponseObject("OK", "Query Object successfully",adOptional)
							):
					ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					new ResponseObject("False", "Can not find category with id = "+id,"")
					);
		}
		
//		Insert data
		/**Với REST API, dữ liệu khi trả về sẽ nằm trong response body, dạng JSON
		 * Status code: một con số báo response có thành công hay không. 
		 * Ví dụ 200 là OK, 401 là không được xác thực, 403 là không đủ quyền, 404 là không tìm thấy.
		 * Header: tương tự request, response cũng có các header
		 * Body: trả về  thêm 1 số thông báo, hay dữ liệu. 
		 * Thì lúc này dữ liệu sẽ được chứa trong response body.
		 * @param category
		 * @return
		 */
		@PostMapping("/add")
		public ResponseEntity<ResponseObject> create(@RequestBody Category category) {
			// Tạo dữ liệu vào bảng từ đối tượng nhận được
			category = categoryService.create(category);
			// Tra ve ResponseEntity
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("OK", "Create category successfully", category));
		}
		

		/**
		 * Update data
		 * @param category
		 * @param id
		 * @return
		 */
		@PutMapping("/{id}")
		public ResponseEntity<ResponseObject> update(@RequestBody Category category, @PathVariable Integer id) {
			// Lấy sinh viên từ DB theo id
			Optional<Category> studentOpt = categoryService.findById(id);
			// isPresent dùng để kiểm tra một đối tượng Optional có không rỗng hay không
			// Nếu có tồn tại category, thực hiện update, ngược lại trả về không tìm thấy
			if (studentOpt.isPresent()) {
				category.getCatId();
				category = categoryService.update(category);
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject("OK", "Update category successfully", category));
			}

			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject("OK", "Cannot find category with id = " + id, ""));
		}
			
		/**
		 * Delete date
		 * @param id
		 * @return
		 */
		@DeleteMapping("/{id}")
		public ResponseEntity<ResponseObject> delete(@PathVariable("id") Integer id) {
			// Tìm đối tượng với id
			Optional<Category> adOptional = categoryService.findById(id);
			// Nếu có tồn tại thực hiện xóa
			if (adOptional.isPresent()) {
				categoryService.delete(id);
				return ResponseEntity.status(HttpStatus.OK)
						.body(new ResponseObject("OK", "Delete category successfully", ""));
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject("FALSE", "Cannot find category with id = " + id, ""));
		}

}
