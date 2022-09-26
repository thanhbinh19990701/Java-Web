package com.web.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.web.entities.Category;
@Service
public interface ICategoryService {
	/**
	 * Lất tất cả loại sản phẩm
	 * @return
	 */
	List<Category> findAll();
	/**
	 * Tìm loại sản phẩm theo ID
	 * @param id
	 * @return
	 */
	Optional<Category> findById(Integer id);
	/**
	 * Tạo loại sản phẩm mới
	 * @param category
	 * @return
	 */
	Category create(Category category);
	
	/**
	 * Cập nhật loại Sản phẩm
	 * @param category
	 * @return
	 */
	Category update(Category category);
	/**
	 * xóa loại sản phẩm theo ID
	 * @param id
	 */
	void delete(Integer id);
}
