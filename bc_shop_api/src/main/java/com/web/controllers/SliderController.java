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

import com.web.entities.Slider;
import com.web.entities.ResponseObject;
import com.web.services.ISliderService;
@RestController
@RequestMapping("/slider")
public class SliderController {
	@Autowired
	ISliderService sliderservice;
	
	@Autowired
	ServletContext servletContext;
	
	/**
	 * Lấy tất cả thông tin của slider
	 * @return
	 */
	@GetMapping("/list")
	List<Slider> getAll(){
		return sliderservice.findAll();
	}
	
	
	/**
	 * Lấy thông tin của slider theo ID
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	ResponseEntity<ResponseObject> findById(@PathVariable Integer id) {
		Optional<Slider> adOptional = sliderservice.findById(id);
		return adOptional.isPresent()?
				 ResponseEntity.status(HttpStatus.OK).body(
							new ResponseObject("OK", "Query Object successfully",adOptional)
						):
				ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				new ResponseObject("False", "Can not find Slider with id = "+id,"")
				);
	}
	

	/*
	 * Insert
	 * @param student
	 * @return
	 */
	@PostMapping("/add")
	public ResponseEntity<ResponseObject> create(@RequestBody Slider slider) {
		// Tạo dữ liệu vào bảng từ đối tượng nhận được
		slider = sliderservice.create(slider);
		// Tra ve ResponseEntity
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject("OK", "Create Slider successfully", slider));
	}
	

	/**
	 * Cập nhật slider
	 * @param slider
	 * @param id
	 * @return
	 */
	@PutMapping("/{id}")
	public ResponseEntity<ResponseObject> update(@RequestBody Slider slider, @PathVariable Integer id) {
		// Lấy Slider từ DB theo id
		Optional<Slider> studentOpt = sliderservice.findById(id);
		// isPresent dùng để kiểm tra một đối tượng Optional có không rỗng hay không
		// Nếu có tồn tại slider, thực hiện update, ngược lại trả về không tìm thấy
		if (studentOpt.isPresent()) {
			slider.getSliId();
			slider = sliderservice.update(slider);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("OK", "Update Slider successfully", slider));
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ResponseObject("OK", "Cannot find Slider with id = " + id, ""));
	}
	

	/**
	 * Xóa slider
	 * @param id
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseObject> delete(@PathVariable("id") Integer id) {
		// Tìm đối tượng với id
		Optional<Slider> adOptional = sliderservice.findById(id);
		// Nếu có tồn tại thực hiện xóa
		if (adOptional.isPresent()) {
			sliderservice.delete(id);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("OK", "Delete Slider successfully", ""));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ResponseObject("FALSE", "Cannot find Slider with id = " + id, ""));
	}
}
