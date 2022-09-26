package com.web.services;

import java.util.List;
import java.util.Optional;

import com.web.entities.Slider;

public interface ISliderService {
	/**
	 * Lấy tất cả slider
	 * @return
	 */
	List<Slider> findAll();
	/**
	 * Lấy thông tin slider theo ID
	 * @param id
	 * @return
	 */
	Optional<Slider> findById(Integer id);
	/**
	 * tạo slider mới
	 * @param slider
	 * @return
	 */
	Slider create(Slider slider);
	/**
	 * Cập nhật slider
	 * @param slider
	 * @return
	 */
	Slider update(Slider slider);
	/**
	 * Xóa slider
	 * @param id
	 */
	void delete(Integer id);
}
