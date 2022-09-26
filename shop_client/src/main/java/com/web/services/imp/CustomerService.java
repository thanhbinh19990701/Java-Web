package com.web.services.imp;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.models.Customer;
import com.web.models.ResponseObject;
import com.web.services.ICustomerService;
@Service
public class CustomerService implements ICustomerService{
	private static RestTemplate rt = new RestTemplate();
	private static ObjectMapper mapper = new ObjectMapper();
	@Override
	public ResponseObject<Customer> getCustomerByMail(Customer customer) {
		String url = "http://localhost:9000/customer/search";
		ResponseObject<Customer> response = new ResponseObject<Customer>();
		String answer;
		try {
			String json = mapper.writeValueAsString(customer);
			// Tạo Header cho http gửi đến server API
			HttpHeaders headers = new HttpHeaders();
			// Kiểu trang là json
			headers.setContentType(MediaType.APPLICATION_JSON);

			// Đại diện cho 1 phản hồi hoặc yêu cầu HTTP, gồm tiêu đề và nội dung
			HttpEntity<String> entity = new HttpEntity<String>(json, headers);
			// Gửi yêu cầu HTTP POST đến server và nhận chuỗi json trả về

			answer = rt.exchange(url, HttpMethod.POST, entity, String.class).getBody();
			
			response = mapper.readValue(answer, new TypeReference<ResponseObject<Customer>>() {});

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}
	@Override
	public ResponseObject<Customer> createCustomer(Customer customer) {
		String url = "http://localhost:9000/customer/add";
		ResponseObject<Customer> response = new ResponseObject<Customer>();
		String answer;
		try {
			String json = mapper.writeValueAsString(customer);
			// Tạo Header cho http gửi đến server API
			HttpHeaders headers = new HttpHeaders();
			// Kiểu trang là json
			headers.setContentType(MediaType.APPLICATION_JSON);

			// Đại diện cho 1 phản hồi hoặc yêu cầu HTTP, gồm tiêu đề và nội dung
			HttpEntity<String> entity = new HttpEntity<String>(json, headers);
			// Gửi yêu cầu HTTP POST đến server và nhận chuỗi json trả về

			answer = rt.exchange(url, HttpMethod.POST, entity, String.class).getBody();
			
			response = mapper.readValue(answer, new TypeReference<ResponseObject<Customer>>() {});

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return response;
	}

}
