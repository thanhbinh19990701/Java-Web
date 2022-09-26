package com.web.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.web.entities.Comment;

public interface ICommentRepository extends JpaRepository<Comment, Integer>{
	@Query("SELECT u FROM Comment u WHERE u.comDeleted = 0 AND u.product.proId = ?1")
	List<Comment> getAllCommentOfProduct(Integer proId);
	@Query("SELECT u FROM Comment u WHERE u.comDeleted = 0 AND u.comId = ?1")
	Optional<Comment> findCommentById(Integer comId);
}
