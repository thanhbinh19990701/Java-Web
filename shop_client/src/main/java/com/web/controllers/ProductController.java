package com.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.models.Product;
import com.web.models.ResponseObject;
import com.web.services.IProductService;


@Controller
@RequestMapping()
public class ProductController {
	@Autowired
	IProductService iProductService;

	/**
	 * lấy sản phẩm theo loại sản phẩm và trả về trang index
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/home")
	public String getAllProduct(Model model) {
		List<Product> listProduct = iProductService.getProductByCategory(1).getData();
		model.addAttribute("listMan",listProduct);
		listProduct = iProductService.getProductByCategory(2).getData();
		model.addAttribute("listWoman",listProduct);
		listProduct = iProductService.getProductByCategory(3).getData();
		model.addAttribute("listBag",listProduct);
		listProduct = iProductService.getProductByCategory(4).getData();
		model.addAttribute("listShoes",listProduct);
		listProduct = iProductService.getProductByCategory(5).getData();
		model.addAttribute("listWatchs",listProduct);
		return "web-client/index";
	} 
	
	/**
	 * lấy chi tiết của sản phẩm theo id truyền vào
	 * @param proId
	 * @param model
	 * @return
	 */
	@GetMapping("/{proId}")
	public String getProductDetail(@PathVariable("proId") Integer proId, Model model) {
		ResponseObject<Product>  response = iProductService.getProductById(proId);
		Product product =  response.getData();
		model.addAttribute("product", product);
		return "web-client/product-detail";
	}
	
	/**
	 * tìm kiếm sản phẩm theo tên gần đúng
	 * @param key
	 * @param model
	 * @return
	 */
	@PostMapping("/search")
	public String searchProduct(@RequestParam("key") String key, Model model ) {
		ResponseObject<List<Product>>  responseSearch = iProductService.seachProductByName(key);
		List<Product> listProduct = responseSearch.getData();
		model.addAttribute("listProduct",listProduct);
		return "web-client/search";
	}

	
}
