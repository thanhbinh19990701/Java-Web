package com.web.services.imp;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.models.Order;
import com.web.models.Product;
import com.web.models.ResponseObject;
import com.web.services.CallApiService;
import com.web.services.IProductService;

@Service
public class ProductService implements IProductService {
	private static RestTemplate rt = new RestTemplate();
	private static ObjectMapper mapper = new ObjectMapper();
	@Autowired
	CallApiService callAPI;
	@Override
	public ResponseObject<List<Product>> getAllProduct() {
		String url = "http://localhost:9000/product/list";
		ResponseObject<List<Product>> response = new ResponseObject<List<Product>>();
		String json = rt.getForObject(url, String.class);
		try {
			response = mapper.readValue(json, new TypeReference<ResponseObject<List<Product>>>() {
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public ResponseObject<Product> getProductById(Integer proId) {
		String url = "http://localhost:9000/product/" + proId;
		ResponseObject<Product> responseProduct = new ResponseObject<Product>();
		String json = rt.getForObject(url, String.class);
		
		try {
			responseProduct = mapper.readValue(json, new TypeReference<ResponseObject<Product>>() {
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseProduct;
	}

	@Override
	public ResponseObject<Product> createProduct(Product product) {
		String url = "http://localhost:9000/product/create";
		ResponseObject<Product> response = new ResponseObject<Product>();
		String answer;
		try {
			String json = mapper.writeValueAsString(product);
			// T???o Header cho http g???i ?????n server API
			HttpHeaders headers = new HttpHeaders();
			// Ki???u trang l?? json
			headers.setContentType(MediaType.APPLICATION_JSON);

			// ?????i di???n cho 1 ph???n h???i ho???c y??u c???u HTTP, g???m ti??u ????? v?? n???i dung
			HttpEntity<String> entity = new HttpEntity<String>(json, headers);
			// G???i y??u c???u HTTP POST ?????n server v?? nh???n chu???i json tr??? v???

			answer = rt.exchange(url, HttpMethod.POST, entity, String.class).getBody();
			
			response = mapper.readValue(answer, new TypeReference<ResponseObject<Product>>() {});

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}

	@Override
	public ResponseObject<Product> updateProduct(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProduct(Integer proId,String cookie) {
		String url = "http://localhost:9000/product/delete/"+proId;
		callAPI.getEntityJSON(url, cookie);
	}
	@Override
	public ResponseObject<List<Product>> seachProductByName(String key) {
		// TODO Auto-generated method stub
		String url = "http://localhost:9000/product/search/"+key;
		ResponseObject<List<Product>> responseSearch = new ResponseObject<List<Product>>();
		String json = rt.getForObject(url, String.class);
		
		try {
			responseSearch = mapper.readValue(json, new TypeReference<ResponseObject<List<Product>>>() {
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseSearch;
	}

	@Override
	public ResponseObject<List<Product>> getProductByCategory(Integer catId) {
		String url = "http://localhost:9000/product/findCategory/" + catId;
		ResponseObject<List<Product>> response = new ResponseObject<List<Product>>();
		String json = rt.getForObject(url, String.class);
		try {
			response = mapper.readValue(json, new TypeReference<ResponseObject<List<Product>>>() {
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}


}
