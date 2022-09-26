package com.web.services.imp;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.models.Order;
import com.web.models.Orderdetail;
import com.web.models.ResponseObject;
import com.web.services.CallApiService;
import com.web.services.IOderService;
@Service
public class OderService implements IOderService{
	private static ObjectMapper mapper = new ObjectMapper();	
	
	@Autowired
	CallApiService callAPI;
	@Override
	public ResponseObject<Order> createOrder(Order order , String cookie) {
		String url = "http://localhost:9000/order/create";
		ResponseObject<Order> response = new ResponseObject<Order>();
		String json = null;
		try {
			json = mapper.writeValueAsString(order);
			String answer = callAPI.postEntityJSON(json, url, cookie);
			response = mapper.readValue(answer, new TypeReference<ResponseObject<Order>>() {});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	@Override
	public ResponseObject<Order> getNewOrder(String cookie) {
		String url = "http://localhost:9000/order/new";
		ResponseObject<Order> response = new ResponseObject<Order>();
		String json = callAPI.getEntityJSON(url, cookie);
		try {
			response = mapper.readValue(json, new TypeReference<ResponseObject<Order>>() {
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	@Override
	public ResponseObject<List<Order>> getAllOrder(String cookie) {
		// TODO Auto-generated method stub
		String url = "http://localhost:9000/order";
		ResponseObject<List<Order>> response = new ResponseObject<List<Order>>();
		String answer;
		try {
			answer = callAPI.getEntityJSON(url, cookie);
			response = mapper.readValue(answer, new TypeReference<ResponseObject<List<Order>>>() {
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	@Override
	public ResponseObject<Order> update(Order order,Integer id, String cookie) {
		String url = "http://localhost:9000/order/update/"+id;
		ResponseObject<Order> response = new ResponseObject<Order>();
		String json = null;
		try {
			json = mapper.writeValueAsString(order);
			String answer = callAPI.putEntityJSON(json, url, cookie);
			response = mapper.readValue(answer, new TypeReference<ResponseObject<Order>>() {});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	@Override
	public void deleteOrder(Integer id, String cookie) {
		String url = "http://localhost:9000/order/delete/"+id;
		ResponseObject<Order> response = new ResponseObject<Order>();
		try {
			String answer = callAPI.getEntityJSON(url, cookie);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
