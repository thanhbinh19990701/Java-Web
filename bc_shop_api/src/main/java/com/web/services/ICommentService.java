package com.web.services;

import java.util.List;
import java.util.Optional;

import com.web.entities.Comment;

public interface ICommentService {
	 /**
	  * Lấy tất cả các comment
	 * @param proId
	 * @return
	 */
	public List<Comment> getCommentOfProduct(Integer proId);
	 /**
	  * Tìm comment theo ID
	 * @param comId
	 * @return
	 */
	public Optional<Comment> findCommentById(Integer comId);
	 /**
	  * Tạo mới comment 
	 * @param comment
	 * @return
	 */
	public Comment createComment(Comment comment);
	 /**
	  * Cập nhật comment
	 * @param comment
	 * @return
	 */
	public Comment updateComment(Comment comment);
	 /**
	  * xóa comment
	 * @param com_id
	 */
	public void deleteComment(Integer com_id);
}
