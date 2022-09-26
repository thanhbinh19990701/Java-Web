package com.web.services.imp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.models.ResponseObject;
import com.web.models.UserAcount;
import com.web.services.AuthService;
import com.web.services.CallApiService;

@Service
public class AuthServiceImp implements AuthService {

	static final String URL_GET_PROFILE = "http://127.0.0.1:9000/login";
	static final String URL_GET_USER = "http://127.0.0.1:9000/login";
	static final String URL_LOGIN = "http://127.0.0.1:9000/login";
	static final String URL_REGISTER = "http://127.0.0.1:8091/api/auth/register";
	static final String URL_UPDATE_PROFILE = "http://127.0.0.1:8091/api/auth//user/update/";
	static final String URL_CHANGE_PASSWORD = "http://127.0.0.1:8091/api/auth//user/change_password/";
	static final String URL_UPLOAD_FILE = "http://127.0.0.1:8091/test/upload";
	static final String URL_AVATAR = "http://127.0.0.1:8091/api/auth/user/avatar/";

	@Autowired
	HttpServletRequest request;
	@Autowired
	HttpServletResponse response;
	@Autowired
	HttpSession session;

	@Autowired
	CallApiService callApiService;
	/**
	 * Ä�á»‘i tÆ°á»£ng Ä‘á»ƒ ghi log tá»« thÆ° viá»‡n slf4j
	 */
	private static final Logger logger = LoggerFactory.getLogger(AuthServiceImp.class);

	/**
	 * Thuá»™c thÆ° viá»‡n jackson Cung cáº¥p cÃ¡c chá»©c nÄƒng Ä‘á»�c ghi JSON thÃ nh POJO object
	 * cÆ¡ báº£n ACCEPT_EMPTY_STRING_AS_NULL_OBJECT: Cháº¥p nháº­n chuá»—i rá»—ng lÃ  Ä‘á»‘i tÆ°á»£ng
	 * Ä‘áº§y Ä‘á»§
	 */
	private static ObjectMapper objectMapper = new ObjectMapper()
			.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

	@Override
	public String checkCookie(String cookieName, Cookie[] cookies) {
		if (cookies != null && cookies.length > 0)
			for (Cookie cookie : cookies) {
				if (cookie.getName().equalsIgnoreCase(cookieName)) {
					return cookie.getValue();
				}
			}
		return "";
	}

	@Override
	public boolean setCookie(String cookie) {
		User user = getProfileAsUser(cookie);
		if (user == null) {
			return false;
		} else {
			PreAuthenticatedAuthenticationToken authToken = new PreAuthenticatedAuthenticationToken(user, cookie,
					user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authToken);
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			authentication.setAuthenticated(true);
			SecurityContext context = SecurityContextHolder.createEmptyContext();
			context.setAuthentication(authentication);
			HttpSession session = request.getSession(true);
			session.setAttribute("SPRING_SECURITY_CONTEXT", context);
			SecurityContextHolder.setContext(context);

			response.addCookie(new Cookie("API_JSESSIONID", cookie));
			return true;
		}
	}

	public User parseInfoToUser(UserAcount info) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(info.getRole()));
		
			User user = new User(info.getPassword(),info.getUsername(), authorities);

		return user;
	}

	@Override
	public ResponseObject<UserAcount> getProfile(String cookie) {
		// Táº¡o ResponseObject<List<StudentModel>> rá»—ng
		ResponseObject<UserAcount> responseObject = new ResponseObject<UserAcount>();

		try {
			String answer = callApiService.getEntityJSON(URL_GET_PROFILE, cookie);
			if (answer != null) {
				responseObject = objectMapper.readValue(answer, new TypeReference<ResponseObject<UserAcount>>() {
				});
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		// return
		return responseObject;
	}

	@Override
	public User getProfileAsUser(String cookie) {
		// Táº¡o ResponseObject<List<StudentModel>> rá»—ng
		ResponseObject<UserAcount> responseObject = new ResponseObject<UserAcount>();

		try {
			String answer = callApiService.getEntityJSON(URL_GET_PROFILE, cookie);
			if (answer != null) {
				responseObject = objectMapper.readValue(answer, new TypeReference<ResponseObject<UserAcount>>() {
				});
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		// return
		if (responseObject.getStatus() != null)
			return parseInfoToUser(responseObject.getData());
		return null;
	}

	@Override
	public ResponseObject<UserAcount> login(UserAcount userAcount) {
		// Táº¡o ResponseObject<List<StudentModel>> rá»—ng
		ResponseObject<UserAcount> responseObject = new ResponseObject<UserAcount>();

		try {
			String answer = callApiService.postEntityJSONWithAuth(userAcount, URL_LOGIN);
			if (answer != null) {
				
				responseObject = objectMapper.readValue(answer, new TypeReference<ResponseObject<UserAcount>>() {
				});
				logger.info("Login successful, username: " + userAcount.getUsername());
				
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		// return
		return responseObject;
	}

	@Override
	public ResponseObject<UserAcount> register(UserAcount userAcount) {
		// Táº¡o ResponseObject<List<StudentModel>> rá»—ng
		ResponseObject<UserAcount> responseObject = new ResponseObject<UserAcount>();
		try {
			
			String jsonModel = objectMapper.writeValueAsString(userAcount);
			String answer = callApiService.postEntityJSON(jsonModel, URL_REGISTER, "");
			// Ä�á»�c chuá»—i json sang object tÆ°Æ¡ng á»©ng
			responseObject = objectMapper.readValue(answer, new TypeReference<ResponseObject<UserAcount>>() {
			});
		} catch (JsonParseException e) {
			logger.error(e.getMessage());
		} catch (JsonMappingException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		// return
		return responseObject;
	}

	@Override
	public ResponseObject<List<UserAcount>> getListUser(String cookie) {
		// Táº¡o ResponseObject<List<StudentModel>> rá»—ng
		ResponseObject<List<UserAcount>> responseObject = new ResponseObject<List<UserAcount>>();
		try {
			String answer = callApiService.getEntityJSON(URL_GET_USER, cookie);
			// Ä�á»�c chuá»—i json sang object tÆ°Æ¡ng á»©ng
			responseObject = objectMapper.readValue(answer, new TypeReference<ResponseObject<List<UserAcount>>>() {
			});
		} catch (JsonParseException e) {
			logger.error(e.getMessage());
		} catch (JsonMappingException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		// return
		return responseObject;
	}

	@Override
	public ResponseEntity<byte[]> getEntityData(String username, String pathAvatar) {
		// Táº¡o ResponseObject<List<StudentModel>> rá»—ng
		if (!pathAvatar.endsWith(".png")) {
			pathAvatar += ".png";
		}
		ResponseEntity<byte[]> response = (ResponseEntity<byte[]>) callApiService
				.getEntityData(URL_AVATAR + username + "/" + pathAvatar, "");

		return response;
	}

	@Override
	public ResponseObject<UserAcount> updateProfile(UserAcount infomationUser, String cookie) {
		// Táº¡o ResponseObject<List<StudentModel>> rá»—ng
		ResponseObject<UserAcount> responseObject = new ResponseObject<UserAcount>();
		try {
//			if (infomationUser.getFileAvatar().length > 0) {
//				String avatar_path = "/" + infomationUser.getUsername() + "/avatar_"
//						+ Calendar.getInstance().getTime().getTime()
//						+ infomationUser.getFileAvatar()[0].getOriginalFilename();
//				infomationUser.setAvatar(avatar_path);
//				infomationUser.setData_avatar(infomationUser.getFileAvatar()[0].getBytes());
//			}
			String jsonModel = objectMapper.writeValueAsString(infomationUser);
			String answer = callApiService.putEntityJSON(jsonModel, URL_UPDATE_PROFILE + infomationUser.getUsername(),
					cookie);
			// Ä�á»�c chuá»—i json sang object tÆ°Æ¡ng á»©ng
			responseObject = objectMapper.readValue(answer, new TypeReference<ResponseObject<UserAcount>>() {
			});
		} catch (JsonParseException e) {
			logger.error(e.getMessage());
		} catch (JsonMappingException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		// return
		return responseObject;
	}

	@Override
	public ResponseObject<String> changePassword(UserAcount account, String cookie) {
		// Táº¡o ResponseObject<List<StudentModel>> rá»—ng
		ResponseObject<String> responseObject = new ResponseObject<String>();
		try {
			String jsonModel = objectMapper.writeValueAsString(account);
			String answer = callApiService.putEntityJSON(jsonModel, URL_CHANGE_PASSWORD + account.getUsername(),
					cookie);
			// Ä�á»�c chuá»—i json sang object tÆ°Æ¡ng á»©ng
			responseObject = objectMapper.readValue(answer, new TypeReference<ResponseObject<String>>() {
			});
		} catch (JsonParseException e) {
			logger.error(e.getMessage());
		} catch (JsonMappingException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		// return
		return responseObject;
	}

}
