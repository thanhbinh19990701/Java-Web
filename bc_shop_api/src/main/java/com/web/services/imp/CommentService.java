package com.web.services.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.entities.Comment;
import com.web.repository.ICommentRepository;
import com.web.services.ICommentService;

@Service
public class CommentService implements ICommentService{
	@Autowired
	ICommentRepository iCommentRepository;
	
	@Override
	public List<Comment> getCommentOfProduct(Integer proId) {
		// TODO Auto-generated method stub
		List<Comment> list = iCommentRepository.getAllCommentOfProduct(proId);
		return list;
	}
	
	@Override
	public Optional<Comment> findCommentById(Integer comId) {
		// TODO Auto-generated method stub
		return iCommentRepository.findCommentById(comId);
	}
	
	@Override
	public Comment createComment(Comment comment) {
		// TODO Auto-generated method stub
		return iCommentRepository.save(comment);
	}

	@Override
	public Comment updateComment(Comment comment) {
		// TODO Auto-generated method stub
		return iCommentRepository.save(comment);
	}

	@Override
	public void deleteComment(Integer com_id) {
		Comment comment = iCommentRepository.findById(com_id).get();
		if(comment != null) {
			comment.setComDeleted((byte)1);
		}
		
	}

	

}
