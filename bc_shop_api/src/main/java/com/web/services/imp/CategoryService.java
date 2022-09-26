package com.web.services.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.entities.Category;
import com.web.repository.ICategoryRepository;
import com.web.services.ICategoryService;
@Service
public class CategoryService implements ICategoryService{

	@Autowired
	ICategoryRepository caterepository;
	
	@Override
	public List<Category> findAll() {
		return caterepository.findAll();
	}

	@Override
	public Optional<Category> findById(Integer id) {
		Optional<Category> optionadmin = caterepository.findById(id);
		return optionadmin;
	}

	@Override
	public Category create(Category category) {
		caterepository.save(category);
		return category;
	}

	@Override
	public Category update(Category admin) {
		caterepository.save(admin);
		return admin;
	}

	@Override
	public void delete(Integer id) {
		Optional<Category> delete = caterepository.findById(id);
		Category category = delete.get();
		if (category !=null) {
			caterepository.delete(category);
		}
	}


}
