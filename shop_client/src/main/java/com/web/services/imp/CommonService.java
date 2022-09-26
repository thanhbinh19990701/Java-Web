package com.web.services.imp;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.services.ICommonService;

@Service
public class CommonService implements ICommonService {
	/**
	 * Đối tượng để ghi log từ thư viện slf4j
	 */

	/**
	 * Thực hiện các yêu cầu HTTP
	 */
	private static RestTemplate restTemplate = new RestTemplate();

	/**
	 * Thuộc thư viện jackson Cung cấp các chức năng đọc ghi JSON thành POJO object
	 * cơ bản ACCEPT_EMPTY_STRING_AS_NULL_OBJECT: Chấp nhận chuỗi rỗng là đối tượng
	 * đầy đủ
	 */
	private static ObjectMapper objectMapper = new ObjectMapper()
			.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

	@Override
	public String getEntityJSON(String URL_GET, String cookie) {
		// Tạo ResponseObject<List<StudentModel>> rỗng
		String answer;
		// Tạo Header cho http gửi đến server API
		HttpHeaders headers = new HttpHeaders();
		// Kiểu trang là json
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add(HttpHeaders.COOKIE, "bezkoder=" + cookie);
		// Đại diện cho 1 phản hồi hoặc yêu cầu HTTP, gồm tiêu đề và nội dung
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		// Gửi yêu cầu HTTP POST đến server và nhận chuỗi json trả về
		try {
			answer = restTemplate.exchange(URL_GET, HttpMethod.GET, entity, String.class).getBody();
		} catch (HttpClientErrorException e) {
			answer = e.getResponseBodyAsString();
		}
		// return
		return answer;
	}

	@Override
	public String postEntityJSON(String URL_POST, Object object, String cookie) throws JsonProcessingException {
		// Tạo ResponseObject<List<StudentModel>> rỗng
		String answer;
		// Tạo Header cho http gửi đến server API
		HttpHeaders headers = new HttpHeaders();
		// Kiểu trang là json
		headers.setContentType(MediaType.APPLICATION_JSON);
		// Xét cookie vào header
		headers.add(HttpHeaders.COOKIE, "bezkoder=" + cookie);
		String jsonBody = objectMapper.writeValueAsString(object);
		// Đại diện cho 1 phản hồi hoặc yêu cầu HTTP, gồm tiêu đề và nội dung
		HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);
		// Gửi yêu cầu HTTP POST đến server và nhận chuỗi json trả về
		try {
			answer = restTemplate.exchange(URL_POST, HttpMethod.POST, entity, String.class).getBody();
		} catch (HttpClientErrorException e) {
			answer = e.getResponseBodyAsString();
		}
		// return
		return answer;
	}

	@Override
	public String putEntityJSON(String URL_PUT, Object object, String cookie) throws JsonProcessingException {
		// Tạo ResponseObject<List<StudentModel>> rỗng
		String answer;
		// Tạo Header cho http gửi đến server API
		HttpHeaders headers = new HttpHeaders();
		// Kiểu trang là json
		headers.setContentType(MediaType.APPLICATION_JSON);
		// Xét cookie vào header
		headers.add(HttpHeaders.COOKIE, "bezkoder=" + cookie);
		String jsonBody = objectMapper.writeValueAsString(object);
		// Đại diện cho 1 phản hồi hoặc yêu cầu HTTP, gồm tiêu đề và nội dung
		HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);
		// Gửi yêu cầu HTTP POST đến server và nhận chuỗi json trả về
		try {
			answer = restTemplate.exchange(URL_PUT, HttpMethod.PUT, entity, String.class).getBody();
		} catch (HttpClientErrorException e) {
			answer = e.getResponseBodyAsString();
		}
		// return
		return answer;
	}

	@Override
	public String deleteEntityJSON(String URL_DELETE, String cookie) {
		// Tạo ResponseObject<List<StudentModel>> rỗng
		String answer;
		// Tạo Header cho http gửi đến server API
		HttpHeaders headers = new HttpHeaders();
		// Kiểu trang là json
		headers.setContentType(MediaType.APPLICATION_JSON);
		// Xét cookie vào header
		headers.add(HttpHeaders.COOKIE, "bezkoder=" + cookie);
		// Đại diện cho 1 phản hồi hoặc yêu cầu HTTP, gồm tiêu đề và nội dung
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		// Gửi yêu cầu HTTP POST đến server và nhận chuỗi json trả về
		try {
			answer = restTemplate.exchange(URL_DELETE, HttpMethod.DELETE, entity, String.class).getBody();
		} catch (HttpClientErrorException e) {
			answer = e.getResponseBodyAsString();
		}
		// return
		return answer;
	}

}