package com.web.services.imp;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.models.Orderdetail;
import com.web.models.ResponseObject;
import com.web.models.UserAcount;
import com.web.services.CallApiService;
import com.web.services.iAccountService;
@Service
public class UserAccountService implements iAccountService{
	@Autowired
	CallApiService callAPI;
	private static ObjectMapper mapper = new ObjectMapper();
	@Override
	public ResponseObject<UserAcount> findUserByID(Integer id, String cookie) {
		String url = "http://localhost:9000/orderdetail/user/" + id;
		ResponseObject<UserAcount> response = new ResponseObject<UserAcount>();
		String answer = null;
		try {
			answer = callAPI.getEntityJSON(url, cookie);
			response = mapper.readValue(answer, new TypeReference<ResponseObject<UserAcount>>() {
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

}
