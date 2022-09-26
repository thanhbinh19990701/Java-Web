package com.web.services;

import java.util.List;

import com.web.models.Product;
import com.web.models.ResponseObject;

public interface IProductService {
	public ResponseObject<List<Product>> getAllProduct();
	public ResponseObject<Product> getProductById(Integer proId) ;
	public ResponseObject<Product> createProduct(Product product) ;
	public ResponseObject<Product> updateProduct(Product product);
	public void deleteProduct(Integer proId,String cookie);
	public ResponseObject<List<Product>> seachProductByName(String key);
	
	public ResponseObject<List<Product>> getProductByCategory(Integer catId);
}
