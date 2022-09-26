package com.web.services;

import java.util.List;
import java.util.Optional;

import com.web.entities.Product;

public interface IProductService {
	/**
	 * Lấy tất cả sản phẩm
	 * @return
	 */
	public List<Product> getAllProduct();
	/**
	 * Lấy thông tin sản phẩm bằng ID
	 * @param proId
	 * @return
	 */
	public Optional<Product> getProductById(Integer proId);
	/**
	 * Tạo mới sản phẩm
	 * @param product
	 * @return
	 */
	public Product createProduct(Product product);
	/**
	 * Cập nhật sản phẩm
	 * @param product
	 * @return
	 */
	public Product updateProduct(Product product);
	/**
	 * Xóa luận lý sản phẩm
	 * @param Id
	 */
	public void deleteProduct(Integer Id);
	/**
	 * Tìm sản phẩm theo tên gần giống
	 * @param proName
	 * @return
	 */
	public List<Product> findProductByName(String proName);
	/**
	 * lấy tất cả sản phẩm theo loại sản phẩm
	 * @param catId
	 * @return
	 */
	public List<Product> findProductByCategory(Integer catId);
}
