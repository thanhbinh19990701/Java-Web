package com.web.services.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.entities.Product;
import com.web.repository.IProductRepository;
import com.web.services.IProductService;

@Service
public class ProductService implements IProductService{
	@Autowired
	IProductRepository iProductRepository;
	
	@Override
	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		return iProductRepository.findAllByproDeleted((byte)0);
	}

	@Override
	public Optional<Product> getProductById(Integer proId) {
		// TODO Auto-generated method stub
		return iProductRepository.findById(proId);
	}

	@Override
	public Product createProduct(Product product) {
		// TODO Auto-generated method stub
		return iProductRepository.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		// TODO Auto-generated method stub
		return  iProductRepository.save(product);
	}

	@Override
	public void deleteProduct(Integer proId) {
		// TODO Auto-generated method stub
		Product product = iProductRepository.findById(proId).get();
		if(product != null) {
			product.setProDeleted((byte)1);
			iProductRepository.save(product);
		}
	}

	@Override
	public List<Product> findProductByName(String proName) {
		// TODO Auto-generated method stub
		return iProductRepository.findProductByName(proName);
	}

	@Override
	public List<Product> findProductByCategory(Integer catId) {
		// TODO Auto-generated method stub
		return iProductRepository.findProductByCategory(catId);
	}
	
	

}
