package com.web.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.entities.Comment;
import com.web.entities.ResponseObject;
import com.web.services.ICommentService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	ICommentService iCommentService;

	/**
	 * Lấy thông tin của comment qua ID
	 * @param proId
	 * @return
	 */
	@GetMapping("/{proId}")
	ResponseEntity<ResponseObject> findById(@PathVariable Integer proId) {

		List<Comment> listComment = iCommentService.getCommentOfProduct(proId);
		if (listComment != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("OK", "Query Product successfully", listComment));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ResponseObject("False", "Can not find Comment in Product with id = " + proId, ""));

	}

	/**
	 * Tạo comment mới
	 * @param comment
	 * @return
	 */
	@PostMapping("/create")
	public ResponseEntity<ResponseObject> create(@RequestBody Comment comment) {
		// Tạo dữ liệu vào bảng từ đối tượng nhận được
		comment = iCommentService.createComment(comment);
		// Tra ve ResponseEntity
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("OK", "Create Comment successfully", comment));
	}

	/**
	 * sửa nội dung của comment
	 * @param comment
	 * @param id
	 * @return
	 */
	@PutMapping("/update/{comId}")
	public ResponseEntity<ResponseObject> update(@RequestBody Comment comment, @PathVariable Integer id) {
		// Lấy comment theo id
		Optional<Comment> commentObj = iCommentService.findCommentById(id);
		// isPresent dùng để kiểm tra một đối tượng Optional có không rỗng hay không
		// Nếu có tồn tại student, thực hiện update, ngược lại trả về không tìm thấy
		if (commentObj.isPresent()) {
			comment = iCommentService.updateComment(comment);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("OK", "Update Comment successfully", comment));
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ResponseObject("OK", "Cannot find Comment with id = " + id, ""));
	}

	/**
	 * Sửa Comment
	 * @param id
	 * @return
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseObject> delete(@PathVariable("id") Integer id) {
		// Tìm đối tượng với id
		Optional<Comment> commentObj = iCommentService.findCommentById(id);
		// Nếu có tồn tại thực hiện xóa
		if (commentObj.isPresent()) {
			iCommentService.deleteComment(id);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("OK", "Delete Comment successfully", ""));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ResponseObject("FALSE", "Cannot find Comment in Product with id = " + id, ""));
	}

}
