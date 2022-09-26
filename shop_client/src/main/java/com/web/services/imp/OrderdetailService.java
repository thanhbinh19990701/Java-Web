package com.web.services.imp;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.models.Orderdetail;
import com.web.models.ResponseObject;
import com.web.services.CallApiService;
import com.web.services.IOrderdetailSerivce;

@Service
public class OrderdetailService implements IOrderdetailSerivce {
	private static ObjectMapper mapper = new ObjectMapper();
	@Autowired
	CallApiService callAPI;

	@Override
	public ResponseObject<Orderdetail> createOrderdetail(Orderdetail orderdetail, String cookie) {
		String url = "http://localhost:9000/orderdetail/add";
		ResponseObject<Orderdetail> response = new ResponseObject<Orderdetail>();
		String answer = null;
		try {
			String json = mapper.writeValueAsString(orderdetail);
			answer = callAPI.postEntityJSON(json, url, cookie);
			response = mapper.readValue(answer, new TypeReference<ResponseObject<Orderdetail>>() {
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public ResponseObject<List<Orderdetail>> getOrderdetail(Integer ordId, String cookie) {
		String url = "http://localhost:9000/orderdetail/detail/" + ordId;
		ResponseObject<List<Orderdetail>> response = new ResponseObject<List<Orderdetail>>();
		String answer = null;
		try {
			answer = callAPI.getEntityJSON(url, cookie);
			response = mapper.readValue(answer, new TypeReference<ResponseObject<List<Orderdetail>>>() {
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

}
