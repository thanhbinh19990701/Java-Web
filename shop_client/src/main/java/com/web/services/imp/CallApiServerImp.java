package com.web.services.imp;

import java.nio.charset.StandardCharsets;

import javax.xml.bind.DatatypeConverter;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.web.models.UserAcount;
import com.web.services.CallApiService;

@Service
public class CallApiServerImp implements CallApiService {

	/**
	 * Đối tượng để ghi log từ thư viện slf4j
	 */
//	private static final Logger logger = LoggerFactory.getLogger(CommonServiceImp.class);
	/**
	 * Thực hiện các yêu cầu HTTP
	 */
	private static RestTemplate restTemplate = new RestTemplate();

	public CallApiServerImp() {
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
	}

	/**
	 * Thuộc thư viện jackson Cung cấp các chức năng đọc ghi JSON thành POJO object
	 * cơ bản ACCEPT_EMPTY_STRING_AS_NULL_OBJECT: Chấp nhận chuỗi rỗng là đối tượng
	 * đầy đủ
	 */

	@Override
	public String getEntityJSON(String URL_GET, String cookie) {
		// Tạo ResponseObject<List<StudentModel>> rỗng
		String answer;

		// Tạo Header cho http gửi đến server API
		HttpHeaders headers = new HttpHeaders();
		// Kiểu trang là json
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add(HttpHeaders.COOKIE, "JSESSIONID=" + cookie);
		// Đại diện cho 1 phản hồi hoặc yêu cầu HTTP, gồm tiêu đề và nội dung
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		// Gửi yêu cầu HTTP POST đến server và nhận chuỗi json trả về
		try {
			answer = restTemplate.exchange(URL_GET, HttpMethod.GET, entity, String.class).getBody();
		} catch (HttpClientErrorException e) {
			answer = e.getResponseBodyAsString();
		}

		// Đọc chuỗi json sang object tương ứng

		// return
		return answer;
	}

	@Override
	public String postEntityJSONWithAuth(UserAcount account, String URL_POST) {
		String answer;

		String str_auth = account.getUsername() + ":" + account.getPassword();
		byte[] message = str_auth.getBytes(StandardCharsets.UTF_8);
		String encoding = "Basic " + DatatypeConverter.printBase64Binary(message);
		// Lấy chuỗi json từ url
		// Tạo Header cho http gửi đến server API
		HttpHeaders headers = new HttpHeaders();
		// Kiểu trang là json
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add(HttpHeaders.AUTHORIZATION, encoding);
		// headers.add(HttpHeaders.AUTHORIZATION, "Basic YmFvOmJhbw==");
		// Đại diện cho 1 phản hồi hoặc yêu cầu HTTP, gồm tiêu đề và nội dung
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		// Gửi yêu cầu HTTP POST đến server và nhận chuỗi json trả về
		try {
			answer = restTemplate.exchange(URL_POST, HttpMethod.POST, entity, String.class).getBody();
		} catch (HttpClientErrorException e) {
			answer = e.getResponseBodyAsString();
		}
		return answer;
	}

	@Override
	public String postEntityJSON(String modelJson, String URL_POST, String cookie) {
		String answer;

		// Lấy chuỗi json từ url
		// Tạo Header cho http gửi đến server API
		HttpHeaders headers = new HttpHeaders();
		// Kiểu trang là json
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add(HttpHeaders.COOKIE, "JSESSIONID=" + cookie);
		// headers.add(HttpHeaders.AUTHORIZATION, "Basic YmFvOmJhbw==");
		// Đại diện cho 1 phản hồi hoặc yêu cầu HTTP, gồm tiêu đề và nội dung
		HttpEntity<String> entity = new HttpEntity<String>(modelJson, headers);
		// Gửi yêu cầu HTTP POST đến server và nhận chuỗi json trả về
		try {
			answer = restTemplate.exchange(URL_POST, HttpMethod.POST, entity, String.class).getBody();
		} catch (HttpClientErrorException e) {
			answer = e.getResponseBodyAsString();
		}
		return answer;
	}

	@Override
	public ResponseEntity<byte[]> getEntityData(String URL_GET, String rangeList) {
		// Tạo ResponseObject<List<StudentModel>> rỗng
		ResponseEntity<byte[]> response;

		// Tạo Header cho http gửi đến server API
		HttpHeaders headers = new HttpHeaders();
		// Kiểu trang là json
		headers.setContentType(MediaType.APPLICATION_JSON);
//				headers.add(HttpHeaders.COOKIE, "JSESSIONID=" + cookie);
		headers.add(HttpHeaders.RANGE, rangeList);
		// Đại diện cho 1 phản hồi hoặc yêu cầu HTTP, gồm tiêu đề và nội dung
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		// Gửi yêu cầu HTTP POST đến server và nhận chuỗi json trả về

		response = restTemplate.exchange(URL_GET, HttpMethod.GET, entity, byte[].class);

		// Đọc chuỗi json sang object tương ứng

		// return
		return response;
	}

	@Override
	public String putEntityJSON(String jsonModel, String URL_PUT, String cookie) {
		String answer;

		// Lấy chuỗi json từ url
		// Tạo Header cho http gửi đến server API
		HttpHeaders headers = new HttpHeaders();
		// Kiểu trang là json
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add(HttpHeaders.COOKIE, "JSESSIONID=" + cookie);
		// headers.add(HttpHeaders.AUTHORIZATION, "Basic YmFvOmJhbw==");
		// Đại diện cho 1 phản hồi hoặc yêu cầu HTTP, gồm tiêu đề và nội dung
		HttpEntity<String> entity = new HttpEntity<String>(jsonModel, headers);
		// Gửi yêu cầu HTTP POST đến server và nhận chuỗi json trả về
		try {
			answer = restTemplate.exchange(URL_PUT, HttpMethod.PUT, entity, String.class).getBody();
		} catch (HttpClientErrorException e) {
			answer = e.getResponseBodyAsString();
		}
		return answer;
	}

}
